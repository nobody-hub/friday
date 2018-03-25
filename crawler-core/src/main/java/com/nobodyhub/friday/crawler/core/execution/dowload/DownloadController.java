package com.nobodyhub.friday.crawler.core.execution.dowload;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.nobodyhub.friday.crawler.core.definition.common.task.Task;
import com.nobodyhub.friday.crawler.core.middleware.kafka.ContentKafkaClient;
import com.nobodyhub.friday.crawler.core.middleware.kafka.LinkKafkaClient;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author Ryan
 */
public class DownloadController {
    protected final Map<Task, List<Future>> tasks = Maps.newHashMap();
    protected final ExecutorService executorService = Executors.newCachedThreadPool();


    public void submit(Task task) {
        Future future = executorService.submit(
                new DownloadExecutor(task, new LinkKafkaClient(), new ContentKafkaClient()));
        addFuture(task, future);
    }

    protected void addFuture(Task task, Future future) {
        List<Future> futures = getFuture(task);
        futures.add(future);
    }

    protected List<Future> getFuture(Task task) {
        List<Future> futures = tasks.get(task);
        if (futures == null) {
            futures = Lists.newArrayList();
            tasks.put(task, futures);
        }
        return futures;
    }

    public void stop(Task task) {
        List<Future> futures = getFuture(task);
        for (Future future : futures) {
            future.cancel(true);
        }
    }
}
