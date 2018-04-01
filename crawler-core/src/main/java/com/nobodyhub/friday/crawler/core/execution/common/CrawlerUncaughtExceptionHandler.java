package com.nobodyhub.friday.crawler.core.execution.common;

/**
 * @author Ryan
 */
public class CrawlerUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        //TODO
        System.out.println(String.format("%s throws UncaughtException!\n %s", t, e));
    }
}
