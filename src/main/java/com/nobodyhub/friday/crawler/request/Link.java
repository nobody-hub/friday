package com.nobodyhub.friday.crawler.request;

/**
 * Links that will be traced by the crawler
 *
 * @author Ryan
 */
public class Link {
    /**
     * url pattern that link need match
     */
    protected String urlPattern;
    /**
     * how to connet that link
     */
    protected Request request;
}
