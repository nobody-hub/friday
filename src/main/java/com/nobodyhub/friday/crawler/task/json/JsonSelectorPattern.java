package com.nobodyhub.friday.crawler.task.json;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.jayway.jsonpath.ReadContext;
import com.nobodyhub.friday.crawler.task.common.ContentType;
import com.nobodyhub.friday.crawler.task.common.SelectorPattern;
import com.nobodyhub.friday.crawler.task.common.SelectorResult;
import com.nobodyhub.friday.crawler.task.json.selector.JsonAttrSelectorPattern;
import com.nobodyhub.friday.crawler.task.json.selector.JsonAudioSelectorPattern;
import com.nobodyhub.friday.crawler.task.json.selector.JsonImageSelectorPattern;
import com.nobodyhub.friday.crawler.task.json.selector.JsonVideoSelectorPattern;
import lombok.EqualsAndHashCode;

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

    public JsonSelectorPattern(ContentType type, String urlPattern, String selector) {
        super(type, urlPattern, selector);
    }

    /**
     * Prefix {@code $..} added to ensure the path will return a list
     *
     * @param document document to be parsed
     * @param result   the parse result
     * @return
     * @see <a href="https://github.com/json-path/JsonPath#what-is-returned-when">what-is-returned-when</a>
     */
    @Override
    protected void select(ReadContext document, SelectorResult result) {
        //TODO: handle if the read returns a list
        result.addAttr(SelectorResult.JSON_VALUE, document.read("$.." + selector));
    }
}
