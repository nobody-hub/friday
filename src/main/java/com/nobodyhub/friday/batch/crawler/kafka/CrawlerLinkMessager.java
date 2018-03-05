package com.nobodyhub.friday.batch.crawler.kafka;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.KafkaException;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.errors.AuthorizationException;
import org.apache.kafka.common.errors.OutOfOrderSequenceException;
import org.apache.kafka.common.errors.ProducerFencedException;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

/**
 * @author Ryan
 */
@Component
public class CrawlerLinkMessager {
    private KafkaProducer<String, String> producer;
    private KafkaConsumer<String, String> consumer;
    private static final String TOPIC = "friday.link";

    @PostConstruct
    private void setup() {
        setupProducer();
        setuoConsumer();
    }

    @PreDestroy
    private void clean() {
        producer.close();
        consumer.close();
    }

    private void setuoConsumer() {
        Properties props = new Properties();
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "friday.crawler");
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        this.consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Lists.newArrayList(TOPIC));
    }

    private void setupProducer() {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        this.producer = new KafkaProducer<>(props);
    }


    public void send(String value) throws ExecutionException, InterruptedException {
        this.producer.send(new ProducerRecord<>(TOPIC, "key:" + value, value)).get();
    }

    public void send(List<? extends String> values) {
        this.producer.initTransactions();
        try {
            this.producer.beginTransaction();
            for (String value : values) {
                this.producer.send(new ProducerRecord<>(TOPIC, value));
            }
            producer.commitTransaction();
        } catch (ProducerFencedException | OutOfOrderSequenceException | AuthorizationException e) {
            // We can't recover from these exceptions, so our only option is to close the producer and exit.
            producer.close();
        } catch (KafkaException e) {
            // For all other exceptions, just abort the transaction and try again.
            producer.abortTransaction();
        }
    }

    public String poll() {
        ConsumerRecords<String, String> records = this.consumer.poll(100);
        for (TopicPartition partition : records.partitions()) {
            List<ConsumerRecord<String, String>> partitionRecords = records.records(partition);
            for (ConsumerRecord<String, String> record : partitionRecords) {
                long offset = record.offset();
                Map<TopicPartition, OffsetAndMetadata> offsets = Maps.newHashMap();
                offsets.put(partition, new OffsetAndMetadata(offset + 1));
                this.consumer.commitSync(offsets);
                return record.value();
            }
        }
        return null;
    }

}
