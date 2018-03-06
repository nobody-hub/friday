package com.nobodyhub.friday.crawler.request;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;
import java.util.Map;

/**
 * Definition of a crawler task
 *
 * @author Ryan
 */
@Data
@EqualsAndHashCode
@ToString
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
     * Task Version
     */
    protected String version;
    /**
     * Task User-Agent
     */
    protected String userAgent;
    /**
     * Entrance urls of crawler
     */
    protected List<String> entranceUrls;
    /**
     * Links that will be traced by the crawler
     * urlPattern => link
     */
    protected Map<String, Link> links;
    /**
     * crawler target in the link
     * urlPattern => target
     */
    protected Map<String, Target> targets;
}
