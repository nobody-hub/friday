package com.nobodyhub.friday.crawler.core.definition.json;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.google.common.collect.Lists;
import com.jayway.jsonpath.ReadContext;
import com.nobodyhub.friday.crawler.core.definition.common.item.Item;
import com.nobodyhub.friday.crawler.core.definition.common.item.ItemPattern;
import com.nobodyhub.friday.crawler.core.definition.common.item.ItemType;
import com.nobodyhub.friday.crawler.core.definition.json.selector.JsonAttrItemPattern;
import com.nobodyhub.friday.crawler.core.definition.json.selector.JsonAudioItemPattern;
import com.nobodyhub.friday.crawler.core.definition.json.selector.JsonImageItemPattern;
import com.nobodyhub.friday.crawler.core.definition.json.selector.JsonVideoItemPattern;
import lombok.EqualsAndHashCode;

import java.util.List;

import static com.nobodyhub.friday.crawler.core.definition.common.item.Item.JSON_VALUE;

/**
 * @author Ryan
 */
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.EXISTING_PROPERTY,
        property = "type",
        visible = true
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = JsonAttrItemPattern.class, name = "TEXT"),
        @JsonSubTypes.Type(value = JsonImageItemPattern.class, name = "IMAGE"),
        @JsonSubTypes.Type(value = JsonAudioItemPattern.class, name = "AUDIO"),
        @JsonSubTypes.Type(value = JsonVideoItemPattern.class, name = "VIDEO")
})
@EqualsAndHashCode(callSuper = true)
public abstract class JsonItemPattern extends ItemPattern<ReadContext> {

    public JsonItemPattern(ItemType type, String urlPattern, List<String> selectors) {
        super(type, urlPattern, selectors);
    }

    /**
     * Prefix {@code $..} added to ensure the path will return a list
     *
     * @param url
     * @param document
     * @param selector
     * @return
     * @see <a href="https://github.com/json-path/JsonPath#what-is-returned-when">what-is-returned-when</a>
     */
    @Override
    protected List<Item> select(String url, ReadContext document, String selector) {
        List<String> values = document.read("$.." + selector);
        List<Item> results = Lists.newArrayList();
        int i = 0;
        for (String value : values) {
            Item result = new Item(this.type, url, selector);
            result.addAttr(JSON_VALUE + i, value);
            results.add(result);
            i++;
        }
        return results;
    }
}
