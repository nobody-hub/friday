package com.nobodyhub.friday.crawler.person;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

/**
 * @author Ryan
 * @since 02/02/2018
 */
@Component
public class PersonTasklet implements Tasklet, InitializingBean {
    private static final Logger logger = LoggerFactory.getLogger(PersonTasklet.class);

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        logger.info("Read Count: " + contribution.getReadCount());
        logger.info("Read Skip Count: " + contribution.getSkipCount());
        logger.info("Write Count: " + contribution.getWriteCount());
        logger.info("Write Skip Count: " + contribution.getWriteSkipCount());
        logger.info("Exit Status: " + contribution.getExitStatus());
        return RepeatStatus.FINISHED;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        logger.info("I am in afterPropertiesSet");
    }
}
