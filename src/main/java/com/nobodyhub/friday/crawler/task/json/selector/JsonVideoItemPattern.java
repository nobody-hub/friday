package com.nobodyhub.friday.crawler.task.json.selector;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nobodyhub.friday.crawler.task.common.item.ItemType;
import com.nobodyhub.friday.crawler.task.json.JsonItemPattern;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author Ryan
 */
@EqualsAndHashCode(callSuper = true)
public class JsonVideoItemPattern extends JsonItemPattern {

    public JsonVideoItemPattern(
            @JsonProperty("urlPattern") String urlPattern,
            @JsonProperty("selectors") List<String> selectors) {
        super(ItemType.VIDEO, urlPattern, selectors);
    }
}
