package com.nobodyhub.friday.crawler.task.common;

import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;
import java.util.regex.Pattern;

/**
 * Links that will be traced by the crawler
 *
 * @author Ryan
 */
@Data
@EqualsAndHashCode
@ToString
public abstract class Link<DOCUMENT, SELECTOR extends Selector<DOCUMENT>> {
    /**
     * url pattern that link need match
     * <p>
     * the {@link SELECTOR#urlPattern} will be ignored
     */
    protected String urlPattern;

    /**
     * the selector to get the link
     */
    protected SELECTOR selector;

    /**
     * how to connet the link
     */
    protected Request request;

    /**
     * whether the given url will be used as a future link or not
     *
     * @param url
     * @return
     */
    public boolean matches(String url) {
        if (url == null) {
            return false;
        }
        return Pattern.compile(urlPattern).matcher(url).find();
    }

    /**
     * get the links for parsing contents
     *
     * @param document
     * @return
     */
    public List<String> parse(DOCUMENT document) {
        return Lists.newArrayList(Collections2.filter(selector.select(document), (input) -> matches(input)));
    }

}
