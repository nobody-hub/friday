package com.nobodyhub.friday.crawler.task.json.selector;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nobodyhub.friday.crawler.task.common.ContentType;
import com.nobodyhub.friday.crawler.task.json.JsonSelector;
import lombok.EqualsAndHashCode;

/**
 * @author Ryan
 */
@EqualsAndHashCode(callSuper = true)
public class JsonAttrSelector extends JsonSelector {
    public JsonAttrSelector(
            @JsonProperty("urlPattern") String urlPattern,
            @JsonProperty("selector") String selector) {
        super(ContentType.TEXT, urlPattern, selector);
    }
}
