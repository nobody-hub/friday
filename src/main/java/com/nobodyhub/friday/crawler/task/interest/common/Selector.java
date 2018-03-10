package com.nobodyhub.friday.crawler.task.interest.common;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.nobodyhub.friday.crawler.task.interest.html.attr.HtmlAttrSelector;
import com.nobodyhub.friday.crawler.task.interest.html.audio.HtmlAudioSelector;
import com.nobodyhub.friday.crawler.task.interest.html.image.HtmlImageSelector;
import com.nobodyhub.friday.crawler.task.interest.html.video.HtmlVideoSelector;
import com.nobodyhub.friday.crawler.task.interest.json.JsonSelector;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

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
        @JsonSubTypes.Type(value = HtmlAttrSelector.class, name = "TEXT"),
        @JsonSubTypes.Type(value = HtmlImageSelector.class, name = "IMAGE"),
        @JsonSubTypes.Type(value = HtmlAudioSelector.class, name = "AUDIO"),
        @JsonSubTypes.Type(value = HtmlVideoSelector.class, name = "VIDEO"),
        @JsonSubTypes.Type(value = JsonSelector.class, name = "JSON"),
})
@RequiredArgsConstructor
@Getter
public abstract class Selector<DOCUMENT, CONTENTTYPE> {
    /**
     * Type of content
     */
    protected final ContentType type;
    /**
     * the selector text
     */
    protected final String selector;

    /**
     * select the interested {@link CONTENTTYPE} from given {@link DOCUMENT}
     *
     * @param document
     * @return
     */
    public abstract List<CONTENTTYPE> select(DOCUMENT document);
}


