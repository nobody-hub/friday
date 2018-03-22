package com.nobodyhub.friday.crawler.executor;

import com.google.common.collect.Maps;
import com.nobodyhub.friday.crawler.core.definition.common.task.Task;

import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Executor will be a webserver running in a docker
 * and be controlled through web API
 *
 * @author Ryan
 */
public class TaskExecutor {
    protected final static int THREAD_LIMIT = 10;
    protected ExecutorService executorService = Executors.newFixedThreadPool(10);
    protected ConcurrentMap<String, Future<Integer>> futures = Maps.newConcurrentMap();

    /**
     * Start threads to process
     */
    public void start(Task task) {

    }

    /**
     * shutdown this
     */
    public void shutdown() {

    }

    /**
     * process progress
     *
     * @return
     */
    public double progress() {
        return 0;
    }
}
