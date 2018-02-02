package com.nobodyhub.friday.crawler.person;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

/**
 * @author Ryan
 * @since 30/01/2018
 */
@Component("processor2")
public class PersonProcessor2 implements ItemProcessor<Person, Object> {


    @Override
    public String process(Person item) throws Exception {
        return item.getFirstName().toUpperCase() + item.getLastName();
    }
}
