package com.nobodyhub.friday.crawler.core.execution;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.nobodyhub.friday.crawler.core.definition.common.task.Task;
import com.nobodyhub.friday.crawler.core.execution.partition.TaskExecutionPartitionAveragePolicy;

import java.util.List;
import java.util.Map;

/**
 * 1. create topis
 * 2. create producer/consumer
 * 3. handle entrance url
 * 4. initiate {@link DownloadExecutor}
 * 5. initiate {@link ParseExecutor} //TODO
 * 6. initiate {@link AnalyzeExecutor} //TODO
 *
 * @author Ryan
 */
public abstract class TaskOperator {
    protected Map<TaskMetadata, List<TaskExecutor>> taskExecutors = Maps.newHashMap();

    /**
     * start task
     *
     * @param task
     */
    public void start(Task task) {
        List<TaskExecutor> executors = TaskExecutor.build(task,
                new TaskExecutionPartitionAveragePolicy(5));
        addTaskExecutors(executors);
    }

    protected void addTaskExecutors(List<TaskExecutor> executors) {
        for (TaskExecutor executor : executors) {
            List<TaskExecutor> existed = taskExecutors.computeIfAbsent(
                    executor.getMetadata(),
                    (taskMetadata) -> Lists.newArrayList());
            existed.add(executor);
        }
    }

    /**
     * stop execution of task
     *
     * @param execution
     */
    public abstract void stop(TaskExecutor execution);

    /**
     * rebalance the task to each TaskExection,
     * when one of the task TaskExecution fails or new Task Executor joins
     *
     * @param executions
     */
    public abstract void rebalance(List<TaskExecutor> executions);
}
