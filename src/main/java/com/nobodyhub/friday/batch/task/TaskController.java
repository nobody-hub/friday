package com.nobodyhub.friday.batch.task;

import com.nobodyhub.friday.batch.crawler.kafka.CrawlerLinkMessager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.JobOperator;
import org.springframework.batch.core.launch.NoSuchJobException;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
import java.util.concurrent.ExecutionException;


/**
 * @author Ryan
 */
@RestController
@RequestMapping("/task")
public class TaskController {
    private static final Logger logger = LoggerFactory.getLogger(TaskController.class);

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private JobExplorer jobExplorer;

    @Autowired
    private JobOperator jobOperator;

    @Autowired
    private Job crawlerJob;

    @Autowired
    private CrawlerLinkMessager messager;

    @RequestMapping("/start")
    public String start() throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException, NoSuchJobException, ExecutionException, InterruptedException {
        messager.send("100");
        jobLauncher.run(crawlerJob, createJobParameters());
        return "Task Started";
    }

    private JobParameters createJobParameters() {
        return new JobParametersBuilder()
                .addString("instance_id", UUID.randomUUID().toString())
                .toJobParameters();
    }
}
