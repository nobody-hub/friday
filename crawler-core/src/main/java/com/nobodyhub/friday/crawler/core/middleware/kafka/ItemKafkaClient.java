package com.nobodyhub.friday.crawler.core.middleware.kafka;

import com.nobodyhub.friday.crawler.core.definition.common.item.Item;
import org.apache.kafka.clients.consumer.KafkaConsumer;

/**
 * @author Ryan
 */
public class ItemKafkaClient extends KafkaClient<Item> {
    @Override
    public KafkaConsumer<String, Item> createConsumer() {
        //TODO
        return null;
    }
}
