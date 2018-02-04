package com.nobodyhub.friday.crawler.person;

import com.nobodyhub.learn.person.PersonBatchConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.job.SimpleJob;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.fail;

/**
 * @author Ryan
 * @since 31/01/2018
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {PersonBatchConfiguration.class})
public class PersonBatchTest {
    private Logger logger = LoggerFactory.getLogger(PersonBatchTest.class);
    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private JobExplorer jobExplorer;

    @Test
    public void testRestart() throws JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {
        SimpleJob job = new SimpleJob("testRestart");
//        job.setRestartable(false);
        JobParameters params = new JobParameters();
        JobExecution firstExecution = jobRepository.createJobExecution(job.getName(), params);
        jobRepository.update(firstExecution);
        try {
            jobRepository.createJobExecution(job.getName(), params);
            fail();
        }catch (JobRestartException e) {
            logger.error(e.getMessage());
        }
    }
}