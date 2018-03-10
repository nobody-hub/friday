package com.nobodyhub.friday.crawler.task.common;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.nobodyhub.friday.crawler.task.html.selector.HtmlAttrSelector;
import com.nobodyhub.friday.crawler.task.html.selector.HtmlAudioSelector;
import com.nobodyhub.friday.crawler.task.html.selector.HtmlImageSelector;
import com.nobodyhub.friday.crawler.task.html.selector.HtmlVideoSelector;
import com.nobodyhub.friday.crawler.task.json.JsonSelector;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.regex.Pattern;

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
        @JsonSubTypes.Type(value = HtmlAttrSelector.class, name = "TEXT"),
        @JsonSubTypes.Type(value = HtmlImageSelector.class, name = "IMAGE"),
        @JsonSubTypes.Type(value = HtmlAudioSelector.class, name = "AUDIO"),
        @JsonSubTypes.Type(value = HtmlVideoSelector.class, name = "VIDEO"),
        @JsonSubTypes.Type(value = JsonSelector.class, name = "JSON"),
})
@RequiredArgsConstructor
@Getter
public abstract class Selector<DOCUMENT> {
    /**
     * Type of content
     */
    protected final ContentType type;
    /**
     * Url REGEX pattern that restrict the target
     */
    protected final String urlPattern;
    /**
     * the selector path
     */
    protected final String selPath;

    /**
     * select the interested content from given {@link DOCUMENT}
     *
     * @param document
     * @return
     */
    public abstract List<String> select(DOCUMENT document);

    /**
     * whether target url matches this interest
     *
     * @param url
     * @return
     */
    public boolean matches(String url) {
        if (url == null) {
            return false;
        }
        return Pattern.compile(urlPattern).matcher(url).find();
    }
}


