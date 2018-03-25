package com.nobodyhub.friday.crawler.core.definition.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nobodyhub.friday.crawler.core.definition.common.link.LinkPattern;
import com.nobodyhub.friday.crawler.core.definition.common.link.Request;
import com.nobodyhub.friday.crawler.core.definition.json.selector.JsonAttrItemPattern;
import lombok.EqualsAndHashCode;

/**
 * Links in JSON
 *
 * @author Ryan
 */
@EqualsAndHashCode(callSuper = true)
public class JsonLinkPattern extends LinkPattern<JsonLinkContent, JsonAttrItemPattern> {
    public JsonLinkPattern(
            @JsonProperty("urlPattern") String urlPattern,
            @JsonProperty("selector") JsonAttrItemPattern selector,
            @JsonProperty("request") Request request) {
        super(urlPattern, selector, request);
    }
}
