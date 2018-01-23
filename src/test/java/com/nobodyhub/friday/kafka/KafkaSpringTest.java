package com.nobodyhub.friday.kafka;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.*;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;


/**
 * @author Ryan
 * @since 23/01/2018
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {KafkaSpringTest.Config.class})
public class KafkaSpringTest {

    @Autowired
    private Listener listener;

    @Autowired
    private KafkaTemplate<Integer, String> template;

    @Test
    public void testSimple() throws Exception {
        template.send("topic1", 0, "yan");
        template.flush();
        assertTrue(this.listener.latch.await(10, TimeUnit.SECONDS));
    }

    @Configuration
    @EnableKafka
    public static class Config extends KafkaBaseTest {
        @Bean
        public ConcurrentKafkaListenerContainerFactory<Integer, String> kafkaListenerContainerFactory() {
            ConcurrentKafkaListenerContainerFactory<Integer, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
            factory.setConsumerFactory(consumerFactory());
            return factory;
        }

        @Bean
        public ConsumerFactory<Integer, String> consumerFactory() {
            return new DefaultKafkaConsumerFactory<>(consumerProps());
        }

        @Bean
        public Listener listener() {
            return new Listener();
        }

        @Bean
        public ProducerFactory<Integer, String> producerFactory() {
            return new DefaultKafkaProducerFactory<>(producerProps());
        }

        @Bean
        public KafkaTemplate<Integer, String> kafkaTemplate() {
            return new KafkaTemplate<>(producerFactory());
        }
    }

    @Component
    public static class Listener {

        private Logger logger = LoggerFactory.getLogger(Listener.class.getName());
        public final CountDownLatch latch = new CountDownLatch(1);

        @KafkaListener(topics = {"topic1"})
        public void listen(String foo) {
            logger.info("received: " + foo);
            this.latch.countDown();
        }
    }
}
