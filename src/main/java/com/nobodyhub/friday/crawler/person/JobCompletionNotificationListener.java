package com.nobodyhub.friday.crawler.person;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.stereotype.Component;

/**
 * @author Ryan
 * @since 30/01/2018
 */
@Component
public class JobCompletionNotificationListener extends JobExecutionListenerSupport {
    private static final Logger logger = LoggerFactory.getLogger(JobCompletionNotificationListener.class);

    @Override
    public void afterJob(JobExecution jobExecution) {
        long timeCollapse = jobExecution.getEndTime().getTime() - jobExecution.getStartTime().getTime();
        if (BatchStatus.COMPLETED.equals(jobExecution.getStatus())) {
            logger.info("!!! JOB FINISHED within " + timeCollapse + "ms!!!");
        }
    }
}
