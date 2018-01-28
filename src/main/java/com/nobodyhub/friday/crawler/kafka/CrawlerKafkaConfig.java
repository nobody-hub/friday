package com.nobodyhub.friday.crawler.kafka;

import com.google.common.collect.Maps;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.IntegerDeserializer;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.*;

import java.util.Map;

import static com.nobodyhub.friday.crawler.kafka.CrawlerKafkaConst.CRAWLER_GROUP_ID;

/**
 * @author Ryan
 * @since 28/01/2018
 */
@Configuration
@EnableKafka
@ComponentScan("com.nobodyhub.friday.crawler.kafka")
public class CrawlerKafkaConfig {
    @Bean
    public ConcurrentKafkaListenerContainerFactory kafkaListenerContainerFactory(ConsumerFactory<Integer, String> consumerFactory) {
        ConcurrentKafkaListenerContainerFactory<Integer, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory);
        return factory;
    }

    @Bean
    public ConsumerFactory<Integer, String> consumerFactory() {
        return new DefaultKafkaConsumerFactory<>(consumerConfigs());
    }

    private Map<String, Object> consumerConfigs() {
        Map<String, Object> configs = Maps.newHashMap();
        configs.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        configs.put(ConsumerConfig.GROUP_ID_CONFIG, CRAWLER_GROUP_ID);
        configs.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, true);
        configs.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, 100);
        configs.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, 15000);
        configs.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, IntegerDeserializer.class);
        configs.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        return configs;
    }

    @Bean
    public ProducerFactory<Integer, String> producerFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfigs());
    }

    private Map<String, Object> producerConfigs() {
        Map<String, Object> configs = Maps.newHashMap();
        configs.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        configs.put(ProducerConfig.RETRIES_CONFIG, 0);
        configs.put(ProducerConfig.BATCH_SIZE_CONFIG, 16384);
        configs.put(ProducerConfig.LINGER_MS_CONFIG, 1);
        configs.put(ProducerConfig.BUFFER_MEMORY_CONFIG, 33554432);
        configs.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, IntegerSerializer.class);
        configs.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        return configs;
    }

    @Bean
    public KafkaTemplate<Integer, String> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }
}
