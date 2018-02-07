package com.nobodyhub.friday.crawler;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.SimpleBatchConfiguration;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.launch.support.SimpleJobOperator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.task.SimpleAsyncTaskExecutor;

/**
 * @author Ryan
 */
@Configuration
@ComponentScan("com.nobodyhub.friday.crawler")
@PropertySource("classpath:crawler.properties")
public class CrawlerBatchConfiguration extends SimpleBatchConfiguration {
    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean("crawlerJob")
    public Job crawlerJob(
            @Qualifier("crawlerStep1") Step crawlerStep1
    ) {
        return jobBuilderFactory.get("friday.crawler")
                .start(crawlerStep1)
                .build();
    }

    @Bean("crawlerStep1")
    public Step crawlerStep1(
            CrawlerLinkReader linkReader,
            CrawlerLinkProcessor linkProcessor,
            CrawlerLinkWriter crawlerLinkWriter
    ) {
        return stepBuilderFactory.get("friday.crawler.crawlerStep1")
                .<Integer, Integer>chunk(1)
                .reader(linkReader)
                .processor(linkProcessor)
                .writer(crawlerLinkWriter)
                .faultTolerant()
                .retry(NumberFormatException.class)
                .retryLimit(100)
                .build();
    }

    @Bean
    public SimpleJobOperator jobOperator() throws Exception {
        SimpleJobOperator jobOperator = new SimpleJobOperator();
        jobOperator.setJobExplorer(jobExplorer());
        jobOperator.setJobRepository(jobRepository());
        jobOperator.setJobRegistry(jobRegistry());
        jobOperator.setJobLauncher(jobLauncher());
        return jobOperator;
    }

    @Override
    @Bean
    public JobLauncher jobLauncher() throws Exception {
        SimpleJobLauncher jobLauncher = new SimpleJobLauncher();
        jobLauncher.setJobRepository(jobRepository());
        jobLauncher.setTaskExecutor(new SimpleAsyncTaskExecutor());
        jobLauncher.afterPropertiesSet();
        return jobLauncher;
    }
}
