package com.nobodyhub.friday.crawler.task.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.Maps;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Map;

/**
 * @author Ryan
 */
@RequiredArgsConstructor
@Getter
public class SelectorResult {
    /**
     * HTML attributes
     */
    public final static String HTML_TEXT = "text";
    public final static String HTML_SRC = "src";
    public final static String HTML_HREF = "href";
    /**
     * JSON attributes
     */
    public final static String JSON_VALUE = "value";

    /**
     * Type of content
     */
    @JsonProperty("type")
    protected final ContentType type;

    /**
     * Url of target page
     */
    @JsonProperty("url")
    protected final String url;

    /**
     * the selector path
     */
    @JsonProperty("selector")
    protected final String selector;

    /**
     * Result attribute map
     */
    protected final Map<String, String> attributeMap = Maps.newHashMap();

    public void addAttr(String attr, String value) {
        attributeMap.put(attr, value);
    }
}
