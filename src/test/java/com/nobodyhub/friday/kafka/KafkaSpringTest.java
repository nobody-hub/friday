package com.nobodyhub.friday.kafka;

import com.nobodyhub.friday.crawler.kafka.CrawlerKafkaConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;


/**
 * @author Ryan
 * @since 23/01/2018
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {CrawlerKafkaConfig.class})
public class KafkaSpringTest {

    @Autowired
    private KafkaTemplate<Integer, String> template;

    @Test
    public void testSimple() {
        template.send("friday.links", 0, "yan" + (new Date()).getTime());
        template.flush();
    }
}
