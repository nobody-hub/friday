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
public class PersonWriter implements ItemWriter<Person> {
    private static final Logger logger = LoggerFactory.getLogger(PersonWriter.class);

    @Override
    public void write(List<? extends Person> items) throws Exception {
        logger.info("writing records... " + items.size() + "records");
        for(Person person: items) {
            logger.info("writing..."+person);
        }
    }
}
