package com.nobodyhub.friday.batch.crawler.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.SkipListener;
import org.springframework.stereotype.Component;

/**
 * @author Ryan
 */
@Component
public class CrawlerStepListener implements SkipListener<String, String> {
    private static final Logger logger = LoggerFactory.getLogger(CrawlerStepListener.class);

    @Override
    public void onSkipInRead(Throwable t) {
        logger.info("skip Read because of {}", t);
    }

    @Override
    public void onSkipInWrite(String item, Throwable t) {
        logger.info("skip Write because of {}", t);
    }

    @Override
    public void onSkipInProcess(String item, Throwable t) {
        logger.info("skip Process because of {}", t);
    }
}
