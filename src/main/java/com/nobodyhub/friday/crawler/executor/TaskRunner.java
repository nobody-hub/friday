package com.nobodyhub.friday.crawler.executor;

import com.google.common.collect.Lists;
import com.nobodyhub.friday.crawler.definition.common.item.Item;
import com.nobodyhub.friday.crawler.definition.common.link.Link;
import com.nobodyhub.friday.crawler.definition.common.task.Task;
import com.nobodyhub.friday.crawler.middleware.kafka.LinkKafkaClient;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.List;
import java.util.concurrent.Callable;

/**
 * @author Ryan
 */
@RequiredArgsConstructor
public class TaskRunner<T extends Task> implements Callable<Integer> {
    /**
     * The definition of the task
     */
    protected final T task;

    /**
     * Kafkf client acts as both consumer and producer for Topic for {@link Link}
     */
    protected final LinkKafkaClient kafkaClient;

    /**
     * @return
     * @throws Exception
     */
    @Override
    public Integer call() throws Exception {
        try (KafkaConsumer<String, Link> consumer = kafkaClient.createConsumer()) {
            while (!Thread.currentThread().isInterrupted()) {
                ConsumerRecords<String, Link> records = consumer.poll(100);
                //commit immediately after consume, regardless whether the records will be processed successfully or not
                consumer.commitSync();
                List<Item> items = Lists.newArrayList();
                for (ConsumerRecord<String, Link> record : records) {
                    List<Link> newLinks = Lists.newArrayList();
                    Link link = record.value();
                    this.task.execute(link, newLinks, items);
                    kafkaClient.send(newLinks);
                }
                //TODO: handle item list
            }
        }
        //TODO: return code
        return 0;
    }
}
