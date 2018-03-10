package com.nobodyhub.friday.crawler.task.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jayway.jsonpath.ReadContext;
import com.nobodyhub.friday.crawler.task.common.Link;
import com.nobodyhub.friday.crawler.task.common.Request;
import com.nobodyhub.friday.crawler.task.json.selector.JsonAttrSelector;
import lombok.EqualsAndHashCode;

/**
 * Links in JSON
 *
 * @author Ryan
 */
@EqualsAndHashCode(callSuper = true)
public class JsonLink extends Link<ReadContext, JsonAttrSelector> {
    public JsonLink(
            @JsonProperty("urlPattern") String urlPattern,
            @JsonProperty("selector") JsonAttrSelector selector,
            @JsonProperty("request") Request request) {
        super(urlPattern, selector, request);
    }
}
