package com.nobodyhub.friday.crawler;

import com.nobodyhub.friday.crawler.kafka.CrawlerLinkMessager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Ryan
 */
@Component
public class CrawlerLinkReader implements ItemReader<Integer> {
    private static final Logger logger = LoggerFactory.getLogger(CrawlerLinkReader.class);

    @Autowired
    private CrawlerLinkMessager messager;

    @Override
    public Integer read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        String value = messager.poll();
        logger.debug("Read value:[{}]", value);
        if (value != null) {
            int val = Integer.parseInt(value);
            if(val >2) {
                return val;
            }
        }
        return null;
    }
}
