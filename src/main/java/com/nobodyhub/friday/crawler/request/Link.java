package com.nobodyhub.friday.crawler.request;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * Links that will be traced by the crawler
 *
 * @author Ryan
 */
@Data
@EqualsAndHashCode
@ToString
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
