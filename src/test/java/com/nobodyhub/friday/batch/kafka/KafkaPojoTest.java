package com.nobodyhub.friday.batch.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.junit.Test;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.KafkaMessageListenerContainer;
import org.springframework.kafka.listener.MessageListener;
import org.springframework.kafka.listener.config.ContainerProperties;

import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import static org.junit.Assert.assertTrue;

/**
 * @author Ryan
 * @since 22/01/2018
 */
public class KafkaPojoTest extends KafkaBaseTest {

    private Logger logger = Logger.getLogger(KafkaPojoTest.class.getName());

    @Test
    public void testAutoCommit() throws Exception {
        logger.info("Start auto");
        ContainerProperties containerProperties = new ContainerProperties("topic1", "topic2");
        final CountDownLatch latch = new CountDownLatch(4);
        containerProperties.setMessageListener(new MessageListener<Integer, String>() {
            public void onMessage(ConsumerRecord<Integer, String> data) {
                logger.info("received:" + data);
                latch.countDown();
            }
        });
        KafkaMessageListenerContainer<Integer, String> container = createContainer(containerProperties);
        container.setBeanName("testAuto");
        container.start();
        Thread.sleep(1000);
        KafkaTemplate<Integer, String> template = createTemplate();
        template.setDefaultTopic("topic1");
        template.sendDefault(0, "foo");
        template.sendDefault(2, "bar");
        template.sendDefault(0, "baz");
        template.sendDefault(2, "qux");
        template.flush();
        assertTrue(latch.await(60, TimeUnit.SECONDS));
        container.stop();
        logger.info("Stop auto");
    }

    private KafkaTemplate<Integer, String> createTemplate() {
        Map<String, Object> props = producerProps();
        DefaultKafkaProducerFactory<Integer, String> pf = new DefaultKafkaProducerFactory<>(props);
        KafkaTemplate<Integer, String> template = new KafkaTemplate<>(pf);
        return template;
    }

    private KafkaMessageListenerContainer<Integer, String> createContainer(ContainerProperties containerProperties) {
        Map<String, Object> props = consumerProps();
        DefaultKafkaConsumerFactory<Integer, String> cf = new DefaultKafkaConsumerFactory(props);
        KafkaMessageListenerContainer<Integer, String> container = new KafkaMessageListenerContainer<>(cf, containerProperties);
        return container;
    }


}
