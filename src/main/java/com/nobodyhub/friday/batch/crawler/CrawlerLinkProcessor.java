package com.nobodyhub.friday.batch.crawler;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

/**
 * @author Ryan
 */
@Component
public class CrawlerLinkProcessor implements ItemProcessor<Integer, Integer> {
    @Override
    public Integer process(Integer item) throws Exception {
        return item/2;
    }
}
