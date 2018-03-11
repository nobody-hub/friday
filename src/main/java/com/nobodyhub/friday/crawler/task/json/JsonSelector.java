package com.nobodyhub.friday.crawler.task.json;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.jayway.jsonpath.ReadContext;
import com.nobodyhub.friday.crawler.task.common.ContentType;
import com.nobodyhub.friday.crawler.task.common.Selector;
import com.nobodyhub.friday.crawler.task.json.selector.JsonAttrSelector;
import com.nobodyhub.friday.crawler.task.json.selector.JsonAudioSelector;
import com.nobodyhub.friday.crawler.task.json.selector.JsonImageSelector;
import com.nobodyhub.friday.crawler.task.json.selector.JsonVideoSelector;
import lombok.EqualsAndHashCode;

import java.util.List;

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
        @JsonSubTypes.Type(value = JsonAttrSelector.class, name = "TEXT"),
        @JsonSubTypes.Type(value = JsonImageSelector.class, name = "IMAGE"),
        @JsonSubTypes.Type(value = JsonAudioSelector.class, name = "AUDIO"),
        @JsonSubTypes.Type(value = JsonVideoSelector.class, name = "VIDEO")
})
@EqualsAndHashCode(callSuper = true)
public abstract class JsonSelector extends Selector<ReadContext> {

    public JsonSelector(ContentType type, String urlPattern, String selector) {
        super(type, urlPattern, selector);
    }

    /**
     * Prefix {@code $..} added to ensure the path will return a list
     *
     * @param document
     * @return
     * @see <a href="https://github.com/json-path/JsonPath#what-is-returned-when">what-is-returned-when</a>
     */
    @Override
    public List<String> select(ReadContext document) {
        return document.read("$.." + selector);
    }
}
