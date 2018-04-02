package com.nobodyhub.friday.crawler.core.execution.dowload;

import com.nobodyhub.friday.crawler.core.definition.common.link.Link;
import com.nobodyhub.friday.crawler.core.definition.common.link.LinkContent;
import com.nobodyhub.friday.crawler.core.execution.TaskExecutor;
import com.nobodyhub.friday.crawler.core.middleware.kafka.ContentKafkaClient;
import com.nobodyhub.friday.crawler.core.middleware.kafka.LinkKafkaClient;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

/**
 * @author Ryan
 */
@RequiredArgsConstructor
public class DownloadRunner implements Runnable {

    protected final TaskExecutor taskExecutor;
    protected final LinkKafkaClient linkKafkaClient;
    protected final ContentKafkaClient contentKafkaClient;

    @Override
    public void run() {
        try (KafkaConsumer<String, Link> consumer = linkKafkaClient.createConsumer()) {
            while (!Thread.currentThread().isInterrupted() && !taskExecutor.isFinished()) {
                ConsumerRecords<String, Link> records = consumer.poll(100);
                for (ConsumerRecord<String, Link> record : records) {
                    Link link = record.value();
                    LinkContent content = taskExecutor.getTask().connect(link);
                    //TODO: send content using contentKafkaClient
                    taskExecutor.countDown();
                }
            }
        } catch (Exception e) {
            //TODO
            e.printStackTrace();
        } finally {
            //TODO
        }
    }
}
