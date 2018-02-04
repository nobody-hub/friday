package com.nobodyhub.friday.crawler;

import com.google.common.collect.Lists;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * @author Ryan
 */
@Component
public class CrawlerLinkReader implements ItemReader<Integer> {
    private List<Integer> numbers;
    private int totalNum = 100;
    private volatile int idx = 0;

    @PostConstruct
    public void setup() {
        numbers = Lists.newArrayList();
        for (int i = 0; i < totalNum; i++) {
            numbers.add(i);
        }
    }

    @Override
    public Integer read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        if (idx < totalNum) {
            return numbers.get(idx++);
        } else {
            idx = 0;
        }
        return null;
    }
}
