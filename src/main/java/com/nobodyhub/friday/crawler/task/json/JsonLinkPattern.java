package com.nobodyhub.friday.crawler.task.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jayway.jsonpath.ReadContext;
import com.nobodyhub.friday.crawler.task.common.LinkPattern;
import com.nobodyhub.friday.crawler.task.common.Request;
import com.nobodyhub.friday.crawler.task.json.selector.JsonAttrSelectorPattern;
import lombok.EqualsAndHashCode;

/**
 * Links in JSON
 *
 * @author Ryan
 */
@EqualsAndHashCode(callSuper = true)
public class JsonLinkPattern extends LinkPattern<ReadContext, JsonAttrSelectorPattern> {
    public JsonLinkPattern(
            @JsonProperty("urlPattern") String urlPattern,
            @JsonProperty("selector") JsonAttrSelectorPattern selector,
            @JsonProperty("request") Request request) {
        super(urlPattern, selector, request);
    }
}
