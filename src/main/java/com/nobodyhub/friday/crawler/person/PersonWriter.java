package com.nobodyhub.friday.crawler.person;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Ryan
 * @since 30/01/2018
 */
@Component
public class PersonWriter implements ItemWriter<Object> {
    private static final Logger logger = LoggerFactory.getLogger(PersonWriter.class);

    @Override
    public void write(List<? extends Object> items) throws Exception {
        logger.info("writing records... " + items.size() + "records");
        for(Object person: items) {
            logger.info("writing..."+person);
        }
    }
}
