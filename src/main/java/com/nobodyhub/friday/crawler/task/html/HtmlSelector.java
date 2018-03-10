package com.nobodyhub.friday.crawler.task.html;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.nobodyhub.friday.crawler.task.common.ContentType;
import com.nobodyhub.friday.crawler.task.common.Selector;
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
        @JsonSubTypes.Type(value = HtmlAttrSelector.class, name = "TEXT"),
        @JsonSubTypes.Type(value = HtmlImageSelector.class, name = "IMAGE"),
        @JsonSubTypes.Type(value = HtmlAudioSelector.class, name = "AUDIO"),
        @JsonSubTypes.Type(value = HtmlVideoSelector.class, name = "VIDEO"),
        @JsonSubTypes.Type(value = HtmlUrlSelector.class, name = "URL")
})
@EqualsAndHashCode(callSuper = true)
public abstract class HtmlSelector extends Selector<Document> {
    public HtmlSelector(ContentType type, String urlPattern, String selector) {
        super(type, urlPattern, selector);
    }
}
