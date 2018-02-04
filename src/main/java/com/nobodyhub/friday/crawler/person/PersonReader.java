package com.nobodyhub.friday.crawler.person;

import com.google.common.collect.Lists;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;


/**
 * @author Ryan
 * @since 30/01/2018
 */
@Component
public class PersonReader implements ItemReader<Person> {
    private List<String> fakeData = Lists.newArrayList();
    private AtomicInteger idx = new AtomicInteger(0);

    @PostConstruct
    protected void init() {
        for (int i = 1; i <= 10; i++) {
            fakeData.add("Name" + i);
        }
    }

    @Override
    public Person read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        Person person = null;
        if (idx.get() != fakeData.size() - 1) {
            person = new Person(fakeData.get(idx.get()), fakeData.get(idx.get() + 1));
            idx.incrementAndGet();
        } else {
            idx.set(0);
        }

        return person;
    }
}
