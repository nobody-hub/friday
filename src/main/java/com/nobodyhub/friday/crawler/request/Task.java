package com.nobodyhub.friday.crawler.request;

import java.util.List;
import java.util.Map;

/**
 * Definition of a crawler task
 *
 * @author Ryan
 */
public class Task {
    /**
     * Task Name
     */
    protected String name;
    /**
     * Task Description
     */
    protected String description;
    /**
     * Task Version (Changed based on requirement)
     */
    protected String version;
    /**
     * Task User-Agent
     */
    protected String userAgent;
    /**
     * Entrance urls of crawler
     */
    protected List<String> entranceUrl;
    /**
     * Links that will be traced by the crawler
     * pattern => link
     */
    protected Map<String, Link> links;
    /**
     * crawler target in the link
     */
    protected Map<String, Target> targets;
}
