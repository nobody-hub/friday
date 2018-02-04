package com.nobodyhub.friday.crawler.person;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.listener.StepExecutionListenerSupport;
import org.springframework.stereotype.Component;

/**
 * @author Ryan
 * @since 04/02/2018
 */
@Component
public class StepSkipCheckingListener extends StepExecutionListenerSupport {
    private Logger logger = LoggerFactory.getLogger(StepSkipCheckingListener.class);

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        logger.info("Entering step:" + stepExecution.getStepName());
        logger.info("\tgetReadCount:" + stepExecution.getReadCount());
        logger.info("\tgetReadSkipCount:" + stepExecution.getReadSkipCount());
        logger.info("\tgetProcessSkipCount:" + stepExecution.getProcessSkipCount());
        logger.info("\tgetWriteCount:" + stepExecution.getWriteCount());
        logger.info("\tgetWriteSkipCount:" + stepExecution.getWriteSkipCount());
        logger.info("\tgetCommitCount:" + stepExecution.getCommitCount());
        logger.info("\tgetSkipCount:" + stepExecution.getSkipCount());
        logger.info("\tgetExitStatus:" + stepExecution.getExitStatus());
        if(stepExecution.getSkipCount()>0) {
            return  new ExitStatus("Complete with Skipped Process");
        }
        return null;
    }

    @Override
    public void beforeStep(StepExecution stepExecution) {
        super.beforeStep(stepExecution);
        logger.info("Entering step:" + stepExecution.getStepName());
        logger.info("\tgetReadCount:" + stepExecution.getReadCount());
        logger.info("\tgetReadSkipCount:" + stepExecution.getReadSkipCount());
        logger.info("\tgetProcessSkipCount:" + stepExecution.getProcessSkipCount());
        logger.info("\tgetWriteCount:" + stepExecution.getWriteCount());
        logger.info("\tgetWriteSkipCount:" + stepExecution.getWriteSkipCount());
        logger.info("\tgetCommitCount:" + stepExecution.getCommitCount());
        logger.info("\tgetSkipCount:" + stepExecution.getSkipCount());
        logger.info("\tgetExitStatus:" + stepExecution.getExitStatus());
    }
}
