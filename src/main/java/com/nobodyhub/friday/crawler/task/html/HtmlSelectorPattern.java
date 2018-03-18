package com.nobodyhub.friday.crawler.task.html;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.nobodyhub.friday.crawler.task.common.ContentType;
import com.nobodyhub.friday.crawler.task.common.SelectorPattern;
import com.nobodyhub.friday.crawler.task.html.selector.*;
import lombok.EqualsAndHashCode;
import org.jsoup.nodes.Document;

/**
 * Links in HTML
 *
 * @author Ryan
 */
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.EXISTING_PROPERTY,
        property = "type",
        visible = true
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = HtmlAttrSelectorPattern.class, name = "TEXT"),
        @JsonSubTypes.Type(value = HtmlImageSelectorPattern.class, name = "IMAGE"),
        @JsonSubTypes.Type(value = HtmlAudioSelectorPattern.class, name = "AUDIO"),
        @JsonSubTypes.Type(value = HtmlVideoSelectorPattern.class, name = "VIDEO"),
        @JsonSubTypes.Type(value = HtmlUrlSelectorPattern.class, name = "URL")
})
@EqualsAndHashCode(callSuper = true)
public abstract class HtmlSelectorPattern extends SelectorPattern<Document> {
    public HtmlSelectorPattern(ContentType type, String urlPattern, String selector) {
        super(type, urlPattern, selector);
    }
}
