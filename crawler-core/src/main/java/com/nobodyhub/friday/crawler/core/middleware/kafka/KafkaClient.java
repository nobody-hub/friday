package com.nobodyhub.friday.crawler.core.middleware.kafka;

import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

/**
 * @author Ryan
 */
public abstract class KafkaClient<T> {
    /**
     * the same reusable instance of {@link KafkaConsumer} by all threads
     */
    protected KafkaProducer<String, T> producer;

    /**
     * Topic on which the client is working
     * TODO: add topic info
     */
    protected final String topic = "TOPIC";


    public void send(T content) {
        ProducerRecord<String, T> record = new ProducerRecord<>(topic, content);
        producer.send(record);
    }

    public void send(T content, Callback callback) {
        ProducerRecord<String, T> record = new ProducerRecord<>(topic, content);
        producer.send(record, callback);
    }

    /**
     * create new consumer instance for each thread
     *
     * @return a new {@link KafkaConsumer} instance on each call
     */
    public abstract KafkaConsumer<String, T> createConsumer();
}
