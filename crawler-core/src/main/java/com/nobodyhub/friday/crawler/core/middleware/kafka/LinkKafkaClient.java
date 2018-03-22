package com.nobodyhub.friday.crawler.core.middleware.kafka;

import com.google.common.collect.Lists;
import com.nobodyhub.friday.crawler.core.definition.common.link.Link;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.util.List;
import java.util.Properties;

/**
 * Kafka client to interact with Link Topic
 *
 * @author Ryan
 */
public class LinkKafkaClient {

    /**
     * the same reusable instance of {@link KafkaConsumer} by all threads
     */
    protected KafkaProducer<String, Link> producer;

    /**
     * Topic on which the client is working
     * TODO: add topic info
     */
    protected final String topic = "TOPIC";

    public LinkKafkaClient() {
        Properties props = new Properties();
        //TODO: add properties for producer
        producer = new KafkaProducer<>(props);
    }

    public void send(List<Link> links) {
        for (Link link : links) {
            //TODO: figure out a meaningful key
            ProducerRecord<String, Link> record = new ProducerRecord<>(topic, link);
            producer.send(record, new ProducerCallback());
        }
    }

    /**
     * @return a new {@link KafkaConsumer} instance on each call
     */
    public KafkaConsumer<String, Link> createConsumer() {
        Properties props = new Properties();
        //broker address, TODO: read from parameter
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "");
        //Consumer Group Id, //TODO: read from parameter
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "");
        //Deserializer of key
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        //Deserializer of value, TODO: implement for Item
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        //fetch from the beginning if no offset found
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        //max 1 record for each poll
        props.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, "1");
        //enable auto commit
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, true);
        //TODO: configure consumer
        // auto commit: off
        KafkaConsumer<String, Link> consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Lists.newArrayList(topic));
        return consumer;
    }

    private class ProducerCallback implements Callback {
        @Override
        public void onCompletion(RecordMetadata metadata, Exception exception) {
            //TODO: handle  callback
            if (exception != null) {
                exception.printStackTrace();
            }
        }
    }
}
