package com.nobodyhub.friday.crawler.task.html;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.nobodyhub.friday.crawler.task.common.item.ItemPattern;
import com.nobodyhub.friday.crawler.task.common.item.ItemType;
import com.nobodyhub.friday.crawler.task.html.selector.*;
import lombok.EqualsAndHashCode;
import org.jsoup.nodes.Document;

import java.util.List;

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
        @JsonSubTypes.Type(value = HtmlAttrItemPattern.class, name = "TEXT"),
        @JsonSubTypes.Type(value = HtmlImageItemPattern.class, name = "IMAGE"),
        @JsonSubTypes.Type(value = HtmlAudioItemPattern.class, name = "AUDIO"),
        @JsonSubTypes.Type(value = HtmlVideoItemPattern.class, name = "VIDEO"),
        @JsonSubTypes.Type(value = HtmlUrlItemPattern.class, name = "URL")
})
@EqualsAndHashCode(callSuper = true)
public abstract class HtmlItemPattern extends ItemPattern<Document> {
    public HtmlItemPattern(ItemType type, String urlPattern, List<String> selectors) {
        super(type, urlPattern, selectors);
    }
}
