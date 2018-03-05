package com.nobodyhub.friday.crawler.request;

import java.util.List;
import java.util.Map;

/**
 * Target of crawler to collect
 *
 * @author Ryan
 */
public class Target {
    /**
     * the url pattern in which the target locates
     */
    protected String urlPattern;
    /**
     * how to select the interested element from the page
     * if empty, the whole contents that retured from url are the actual target
     */
    protected List<String> selectors;
    /**
     * attributes in which crawler is interested
     * if attribute for selector is empty, the whole element that is selected are the target
     */
    protected Map<String, List<Attribute>> attributes;
}
