package com.nobodyhub.friday.crawler.task.common;

import com.google.common.collect.Lists;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.List;

/**
 * Definition of a crawler task
 *
 * @author Ryan
 */
@Data
@EqualsAndHashCode
@ToString
@RequiredArgsConstructor
public abstract class Task<
        DOCUMENT,
        SELECTOR extends Selector<DOCUMENT>> {
    /**
     * Type of task
     */
    protected final TaskType taskType;
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
     */
    protected List<Link> links;
    /**
     * selectors to select interested contents
     */
    protected List<SELECTOR> selectors;

    /**
     * Get futher links
     *
     * @param document
     * @return
     */
    public abstract List<String> parseLink(DOCUMENT document);

    /**
     * Get contents of selectors
     *
     * @param document
     * @return
     */
    public List<String> parseContent(String url, DOCUMENT document) {
        List<String> values = Lists.newArrayList();
        for (SELECTOR target : selectors) {
            if (target.matches(url)) {
                values.addAll(target.select(document));
            }
        }
        return values;
    }

    /**
     * add selPath to this interest
     *
     * @param selector
     */
    public void addSelector(SELECTOR selector) {
        this.selectors.add(selector);
    }
}
