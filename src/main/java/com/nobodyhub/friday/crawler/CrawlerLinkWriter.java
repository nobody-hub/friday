package com.nobodyhub.friday.crawler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Ryan
 */
@Component
public class CrawlerLinkWriter implements ItemWriter<Integer> {
    private static final Logger logger = LoggerFactory.getLogger(CrawlerLinkWriter.class);

    @Override
    public void write(List<? extends Integer> items) throws Exception {
        logger.info("Writing {} records", items.size());
        for (Integer item : items) {
            logger.info("Writing item[{}] ...", item);
        }
    }
}
