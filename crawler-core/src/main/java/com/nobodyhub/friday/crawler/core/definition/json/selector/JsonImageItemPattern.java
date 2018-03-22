package com.nobodyhub.friday.crawler.core.definition.json.selector;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nobodyhub.friday.crawler.core.definition.common.item.ItemType;
import com.nobodyhub.friday.crawler.core.definition.json.JsonItemPattern;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author Ryan
 */
@EqualsAndHashCode(callSuper = true)
public class JsonImageItemPattern extends JsonItemPattern {

    public JsonImageItemPattern(
            @JsonProperty("urlPattern") String urlPattern,
            @JsonProperty("selectors") List<String> selectors) {
        super(ItemType.IMAGE, urlPattern, selectors);
    }
}
