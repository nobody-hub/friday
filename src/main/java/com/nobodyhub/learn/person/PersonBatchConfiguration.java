package com.nobodyhub.learn.person;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.partition.support.Partitioner;
import org.springframework.batch.core.partition.support.SimplePartitioner;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;

/**
 * @author Ryan
 * @since 30/01/2018
 */
@Configuration
@EnableBatchProcessing
@ComponentScan("com.nobodyhub.friday.crawler.person")
public class PersonBatchConfiguration /*extends SimpleBatchConfiguration implements BatchConfigurer*/ {
    @Autowired
    protected JobBuilderFactory jobBuilderFactory;

    @Autowired
    protected StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job importUserJob(
            JobCompletionNotificationListener listener,
            @Qualifier("step1") Step step1,
            @Qualifier("step2") Step step2,
            @Qualifier("step3") Step step3,
            @Qualifier("partitionStep.master") Step partitionStep) {
        return jobBuilderFactory.get("importUserJob")
                .incrementer(new RunIdIncrementer())
                .start(partitionStep)
                .listener(listener)
//                .start(step1)
//                .on("*Skip*").to(step2)
//                .from(step1).on("*").to(step3)
//                .end()
                .build();
    }

    @Bean("partitionStep.master")
    public Step partitionStepMaster(@Qualifier("partitionStep.slave") Step partitionStepSlave) {
        return stepBuilderFactory.get("partitionStep.master")
                .partitioner("slaveStep", partitioner())
                .step(partitionStepSlave)
                .taskExecutor(taskExecutor())
                .gridSize(1)
                .build();

    }

    @Bean
    public Partitioner partitioner() {
        return new SimplePartitioner();
    }

    @Bean("partitionStep.slave")
    public Step partitionStepSlave(
            ItemReader<Person> reader,
            @Qualifier("processor2") ItemProcessor<Person, Object> processor,
            ItemWriter<Object> writer,
            StepExecutionListener listener) {
        return stepBuilderFactory.get("partitionStep.slave")
                .listener(listener)
                .<Person, Object>chunk(1)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();
    }

    @Bean
    public TaskExecutor taskExecutor() {
        return new SimpleAsyncTaskExecutor();
    }


    @Bean("step1")
    public Step step1(
            ItemReader<Person> reader,
            @Qualifier("processor1") ItemProcessor<Person, Person> processor,
            ItemWriter<Object> writer,
            StepExecutionListener listener) {
        return stepBuilderFactory.get("step1")
                .listener(listener)
                .<Person, Person>chunk(1)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .faultTolerant()
                .skipLimit(100)
                .skip(Exception.class)
                .build();
    }

    @Bean("step2")
    public Step step2(
            ItemReader<Person> reader,
            @Qualifier("processor2") ItemProcessor<Person, Object> processor,
            ItemWriter<Object> writer) {
        return stepBuilderFactory.get("step2")
                .<Person, Object>chunk(5)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();
    }

    @Bean("step3")
    public Step step3(PersonTasklet tasklet) {
        return stepBuilderFactory.get("step3")
                .tasklet(tasklet)
                .build();
    }


//
//    @Bean
//    @Override
//    public PlatformTransactionManager getTransactionManager() throws Exception {
//        return new ResourcelessTransactionManager();
//    }
//
//    @Bean
//    public MapJobRepositoryFactoryBean mapJobRepositoryFactoryBean() throws Exception {
//        MapJobRepositoryFactoryBean mapJobRepositoryFactoryBean = new MapJobRepositoryFactoryBean();
//        mapJobRepositoryFactoryBean.setTransactionManager(getTransactionManager());
//        return mapJobRepositoryFactoryBean;
//    }
//
//
//    @Bean
//    @Override
//    public JobRepository getJobRepository() throws Exception {
//        return mapJobRepositoryFactoryBean().getObject();
//    }
//
//    @Bean
//    @Override
//    public JobLauncher getJobLauncher() throws Exception {
//        SimpleJobLauncher jobLauncher = new SimpleJobLauncher();
//        jobLauncher.setJobRepository(getJobRepository());
//        jobLauncher.setTaskExecutor(new SimpleAsyncTaskExecutor());
//        jobLauncher.afterPropertiesSet();
//        return jobLauncher;
//    }
//
//    @Bean
//    @Override
//    public JobExplorer getJobExplorer() throws Exception {
//        MapJobExplorerFactoryBean factoryBean = new MapJobExplorerFactoryBean();
//        factoryBean.setRepositoryFactory(mapJobRepositoryFactoryBean());
//        return factoryBean.getObject();
//    }
//
//    @Override
//    @Bean
//    public JobRegistry jobRegistry() throws Exception {
//        return new MapJobRegistry();
//    }
//
//    @Bean
//    public JobRegistryBeanPostProcessor jobRegistryBeanPostProcessor() throws Exception {
//        JobRegistryBeanPostProcessor postProcessor = new JobRegistryBeanPostProcessor();
//        postProcessor.setJobRegistry(jobRegistry());
//        return postProcessor;
//    }
//
//    @Bean
//    public SimpleJobOperator jobOperator() throws Exception {
//        SimpleJobOperator jobOperator = new SimpleJobOperator();
//        jobOperator.setJobExplorer(jobExplorer());
//        jobOperator.setJobRegistry(jobRegistry());
//        jobOperator.setJobRepository(getJobRepository());
//        jobOperator.setJobLauncher(getJobLauncher());
//        return jobOperator;
//    }
}
