package com.nobodyhub.friday.crawler.kafka;

import com.nobodyhub.learn.kafka.CrawlerKafkaConfig;
import com.nobodyhub.learn.kafka.CrawlerKafkaConst;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Ryan
 * @since 29/01/2018
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {CrawlerKafkaConfig.class})
public class CrawlerKafkaConfigTest {


    @Autowired
    private KafkaConsumer<Integer, String> consumer;

    @Autowired
    private KafkaProducer<Integer, String> producer;

    @Test
    public void testConsumer() {
        AtomicInteger cnt = new AtomicInteger(3);
        while (cnt.get() > 0) {
            ConsumerRecords<Integer, String> consumerRecords = consumer.poll(1000);
            consumerRecords.forEach((record) -> {
                System.out.printf("Consumer Record:(Topic -> %s, Key -> %d, Value -> %s, Partition -> %d, Offset -> %d)\n",
                        record.topic(),
                        record.key(),
                        record.value(),
                        record.partition(),
                        record.offset());
                cnt.getAndDecrement();
            });
            consumer.commitAsync();
        }
        consumer.close();
    }

    @Test
    public void testProducer() throws ExecutionException, InterruptedException {
        int time = (int) System.currentTimeMillis();
        int cnt = 3;
        try {
            for (int index = time; index < time + cnt; index++) {
                final ProducerRecord<Integer, String> record = new ProducerRecord<>(CrawlerKafkaConst.CRAWLER_TOPIC, index, "Hello Yan" + index);
                RecordMetadata metadata = producer.send(record).get();
                System.out.printf("Sent Record(Key -> %d, Value -> %s, partition -> %d, offset -> %d", record.key(), record.value(), metadata.partition(), metadata.offset());
            }
        } finally {
            producer.close();
            producer.flush();
        }
    }


}