package com.nobodyhub.friday.crawler.task.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jayway.jsonpath.ReadContext;
import com.nobodyhub.friday.crawler.task.common.link.LinkPattern;
import com.nobodyhub.friday.crawler.task.common.link.Request;
import com.nobodyhub.friday.crawler.task.json.selector.JsonAttrItemPattern;
import lombok.EqualsAndHashCode;

/**
 * Links in JSON
 *
 * @author Ryan
 */
@EqualsAndHashCode(callSuper = true)
public class JsonLinkPattern extends LinkPattern<ReadContext, JsonAttrItemPattern> {
    public JsonLinkPattern(
            @JsonProperty("urlPattern") String urlPattern,
            @JsonProperty("selector") JsonAttrItemPattern selector,
            @JsonProperty("request") Request request) {
        super(urlPattern, selector, request);
    }
}
