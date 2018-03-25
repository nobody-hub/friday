package com.nobodyhub.friday.crawler.core.middleware.kafka;

import com.nobodyhub.friday.crawler.core.definition.common.link.LinkContent;
import org.apache.kafka.clients.consumer.KafkaConsumer;

/**
 * @author Ryan
 */
public class ContentKafkaClient extends KafkaClient<LinkContent> {

    @Override
    public KafkaConsumer<String, LinkContent> createConsumer() {
        //TODO
        return null;
    }
}
