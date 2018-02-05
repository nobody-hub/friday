package com.nobodyhub.friday.crawler.kafka;

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
import org.apache.kafka.common.serialization.IntegerDeserializer;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * @author Ryan
 */
@Component
public class CrawlerLinkMessager {
    private KafkaProducer<Integer, Integer> producer;
    private KafkaConsumer<Integer, Integer> consumer;
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
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "friday.crawler");
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, true);
        props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, 1000);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, IntegerDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, IntegerDeserializer.class);
        this.consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Lists.newArrayList(TOPIC));
    }

    private void setupProducer() {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ProducerConfig.ACKS_CONFIG, "all");
        props.put(ProducerConfig.RETRIES_CONFIG, "0");
        props.put(ProducerConfig.TRANSACTIONAL_ID_CONFIG, "friday.crawler.transaction");
        props.put(ProducerConfig.BATCH_SIZE_CONFIG, 16384);
        props.put(ProducerConfig.LINGER_MS_CONFIG, 1);
        props.put(ProducerConfig.BUFFER_MEMORY_CONFIG, 33554432);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, IntegerSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, IntegerSerializer.class);
        this.producer = new KafkaProducer<>(props);
    }


    public void send(Integer value) {
        this.producer.send(new ProducerRecord<>(TOPIC, value));
    }

    public void send(List<Integer> values) {
        this.producer.initTransactions();
        try{
            this.producer.beginTransaction();
            for(Integer value: values) {
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

    public Integer poll() {
        ConsumerRecords<Integer, Integer> records = this.consumer.poll(100);
        for (TopicPartition partition : records.partitions()) {
            List<ConsumerRecord<Integer, Integer>> partitionRecords = records.records(partition);
            for (ConsumerRecord<Integer, Integer> record : partitionRecords) {
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
