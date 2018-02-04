package com.nobodyhub.learn.person;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

/**
 * @author Ryan
 * @since 30/01/2018
 */
@Component("processor1")
public class PersonProcessor1 implements ItemProcessor<Person, Person>{


    @Override
    public Person process(Person item) throws Exception {
        throw new Exception("I am Exception");
//        return new Person(item.getFirstName().toUpperCase(), item.getLastName().toLowerCase());
    }
}
