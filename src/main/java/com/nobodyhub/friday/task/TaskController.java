package com.nobodyhub.friday.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.*;
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

import java.util.List;
import java.util.UUID;

import static org.springframework.batch.core.BatchStatus.COMPLETED;


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

    @RequestMapping("/start")
    public String start() throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException, NoSuchJobException {
        jobLauncher.run(crawlerJob, createJobParameters());
        jobLauncher.run(crawlerJob, createJobParameters());
        JobExecution execution = jobLauncher.run(crawlerJob, createJobParameters());
        while (!COMPLETED.equals(execution.getStatus())) {
            for (JobInstance instance : jobExplorer.getJobInstances("friday.crawler", 0, 100)) {
                List<JobExecution> execs = jobExplorer.getJobExecutions(instance);
                logger.info("\tInstance[{}:{}] has {} executions, which are:",
                        instance.getInstanceId(),
                        instance.getJobName());
                for (JobExecution exec : execs) {
                    logger.info("Execution[{}:{}:{}]", exec.getJobId(), exec.getId(), exec.getStatus());
                }
            }
        }
        return "Task Started";
    }

    private JobParameters createJobParameters() {
        return new JobParametersBuilder()
                .addString("instance_id", UUID.randomUUID().toString())
                .toJobParameters();
    }
}
