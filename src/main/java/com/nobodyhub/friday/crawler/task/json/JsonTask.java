package com.nobodyhub.friday.crawler.task.json;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.Lists;
import com.jayway.jsonpath.ReadContext;
import com.nobodyhub.friday.crawler.task.common.Task;
import com.nobodyhub.friday.crawler.task.common.TaskType;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author Ryan
 */
@EqualsAndHashCode(callSuper = true)
public class JsonTask
        extends Task<ReadContext, JsonSelector, JsonLinkPattern> {
    public JsonTask(
            String name,
            String description,
            String version,
            String userAgent) {
        super(TaskType.JSON,
                name,
                description,
                version,
                userAgent,
                Lists.newArrayList(),
                Lists.newArrayList(),
                Lists.newArrayList());
    }

    @JsonCreator
    public JsonTask(
            @JsonProperty("name") String name,
            @JsonProperty("description") String description,
            @JsonProperty("version") String version,
            @JsonProperty("userAgent") String userAgent,
            @JsonProperty("entranceUrls") List<String> entranceUrls,
            @JsonProperty("htmlLinks") List<JsonLinkPattern> jsoNLinks,
            @JsonProperty("htmlSelectors") List<JsonSelector> jsonSelectors) {
        super(TaskType.JSON,
                name,
                description,
                version,
                userAgent,
                entranceUrls,
                jsoNLinks,
                jsonSelectors);
    }
}
