package com.nobodyhub.friday.crawler.task;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.regex.Pattern;

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

    public boolean matches(String url) {
        if (url == null) {
            return false;
        }
        return Pattern.compile(urlPattern).matcher(url).find();
    }
}
