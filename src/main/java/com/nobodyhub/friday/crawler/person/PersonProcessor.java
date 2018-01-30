package com.nobodyhub.friday.crawler.person;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

/**
 * @author Ryan
 * @since 30/01/2018
 */
@Component
public class PersonProcessor implements ItemProcessor<Person, Person>{


    @Override
    public Person process(Person item) throws Exception {
        return new Person(item.getFirstName().toUpperCase(), item.getLastName().toLowerCase());
    }
}
