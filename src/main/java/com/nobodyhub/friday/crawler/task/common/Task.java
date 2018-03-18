package com.nobodyhub.friday.crawler.task.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.google.common.collect.Lists;
import com.nobodyhub.friday.crawler.task.html.HtmlTask;
import com.nobodyhub.friday.crawler.task.json.JsonTask;
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
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.EXISTING_PROPERTY,
        property = "taskType",
        visible = true
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = HtmlTask.class, name = "HTML"),
        @JsonSubTypes.Type(value = JsonTask.class, name = "JSON")
})
@Data
@EqualsAndHashCode
@ToString
@RequiredArgsConstructor
public abstract class Task<
        DOCUMENT,
        SELECTOR extends Selector<DOCUMENT>,
        LINK extends LinkPattern<DOCUMENT, ? extends SELECTOR>> {
    /**
     * Type of task
     */
    @JsonProperty("taskType")
    protected final TaskType taskType;
    /**
     * Task Name
     */
    @JsonProperty("name")
    protected final String name;
    /**
     * Task Description
     */
    @JsonProperty("description")
    protected final String description;
    /**
     * Task Version
     */
    @JsonProperty("version")
    protected final String version;
    /**
     * Task User-Agent
     */
    @JsonProperty("userAgent")
    protected final String userAgent;
    /**
     * Entrance urls of crawler
     */
    @JsonProperty("entranceUrls")
    protected final List<String> entranceUrls;
    /**
     * Links that will be traced by the crawler
     */
    @JsonProperty("links")
    protected final List<LINK> links;
    /**
     * selectors to select interested contents
     */
    @JsonProperty("selectors")
    protected final List<SELECTOR> selectors;

    /**
     * Get futher links
     *
     * @param document
     * @return
     */
    public List<String> parseLink(DOCUMENT document) {
        List<String> urls = Lists.newArrayList();
        for (LINK link : links) {
            urls.addAll(link.parse(document));
        }
        return urls;
    }

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

    public void addEntranceUrl(String url) {
        this.entranceUrls.add(url);
    }

    /**
     * add selector to select interested contents
     *
     * @param selector
     */
    public void addSelector(SELECTOR selector) {
        this.selectors.add(selector);
    }

    /**
     * add link to fetch more contents
     *
     * @param link
     */
    public void addLink(LINK link) {
        this.links.add(link);
    }
}
