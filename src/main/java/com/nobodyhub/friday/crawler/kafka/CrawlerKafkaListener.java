package com.nobodyhub.friday.crawler.kafka;

import org.apache.log4j.Logger;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import static com.nobodyhub.friday.crawler.kafka.CrawlerKafkaConst.CRAWLER_TOPIC;

/**
 * @author Ryan
 * @since 28/01/2018
 */
@Component
public class CrawlerKafkaListener {
    private Logger logger = Logger.getLogger(CrawlerKafkaListener.class);

    @KafkaListener(topics = {CRAWLER_TOPIC})
    public void listen(String value) {
        logger.error(String.format("Handling: [%s] ...", value));
    }
}
