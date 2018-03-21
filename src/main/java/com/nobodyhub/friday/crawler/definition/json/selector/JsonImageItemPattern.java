package com.nobodyhub.friday.crawler.definition.json.selector;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nobodyhub.friday.crawler.definition.common.item.ItemType;
import com.nobodyhub.friday.crawler.definition.json.JsonItemPattern;
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
