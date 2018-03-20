package com.nobodyhub.friday.crawler.task.json;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.google.common.collect.Lists;
import com.jayway.jsonpath.ReadContext;
import com.nobodyhub.friday.crawler.task.common.ContentType;
import com.nobodyhub.friday.crawler.task.common.SelectorPattern;
import com.nobodyhub.friday.crawler.task.common.SelectorResult;
import com.nobodyhub.friday.crawler.task.json.selector.JsonAttrSelectorPattern;
import com.nobodyhub.friday.crawler.task.json.selector.JsonAudioSelectorPattern;
import com.nobodyhub.friday.crawler.task.json.selector.JsonImageSelectorPattern;
import com.nobodyhub.friday.crawler.task.json.selector.JsonVideoSelectorPattern;
import lombok.EqualsAndHashCode;

import java.util.List;

import static com.nobodyhub.friday.crawler.task.common.SelectorResult.JSON_VALUE;

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
        @JsonSubTypes.Type(value = JsonAttrSelectorPattern.class, name = "TEXT"),
        @JsonSubTypes.Type(value = JsonImageSelectorPattern.class, name = "IMAGE"),
        @JsonSubTypes.Type(value = JsonAudioSelectorPattern.class, name = "AUDIO"),
        @JsonSubTypes.Type(value = JsonVideoSelectorPattern.class, name = "VIDEO")
})
@EqualsAndHashCode(callSuper = true)
public abstract class JsonSelectorPattern extends SelectorPattern<ReadContext> {

    public JsonSelectorPattern(ContentType type, String urlPattern, List<String> selectors) {
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
    protected List<SelectorResult> select(String url, ReadContext document, String selector) {
        List<String> values = document.read("$.." + selector);
        List<SelectorResult> results = Lists.newArrayList();
        int i = 0;
        for (String value : values) {
            SelectorResult result = new SelectorResult(this.type, url, selector);
            result.addAttr(JSON_VALUE + i, value);
            i++;
        }
        return results;
    }
}
