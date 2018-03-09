package com.nobodyhub.friday.crawler.request.interest.common;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.google.common.collect.Lists;
import com.nobodyhub.friday.crawler.request.interest.html.attr.HtmlAttrInterest;
import com.nobodyhub.friday.crawler.request.interest.html.audio.HtmlAudioInterest;
import com.nobodyhub.friday.crawler.request.interest.html.image.HtmlImageInterest;
import com.nobodyhub.friday.crawler.request.interest.html.video.HtmlVideoInterest;
import com.nobodyhub.friday.crawler.request.interest.json.JsonInterest;
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
        @JsonSubTypes.Type(value = HtmlAttrInterest.class, name = "TEXT"),
        @JsonSubTypes.Type(value = HtmlImageInterest.class, name = "IMAGE"),
        @JsonSubTypes.Type(value = HtmlAudioInterest.class, name = "AUDIO"),
        @JsonSubTypes.Type(value = HtmlVideoInterest.class, name = "VIDEO"),
        @JsonSubTypes.Type(value = JsonInterest.class, name = "JSON"),
})
@Getter
@RequiredArgsConstructor
public abstract class Interest<CONTENTTYPE, DOCUMENT, SELECTOR extends Selector<DOCUMENT, CONTENTTYPE>> {
    /**
     * Type of content
     */
    protected final ContentType type;

    /**
     * selectors used to locate interet point
     */
    protected final List<SELECTOR> selectors = Lists.newArrayList();

    /**
     * Url REGEX pattern that restrict the target
     */
    protected final String urlPattern;

    /**
     * Fetch all contents in that are interested
     *
     * @param document
     * @return
     */
    public abstract List<CONTENTTYPE> fetch(DOCUMENT document);

    /**
     * add selector to this interest
     *
     * @param selector
     */
    public void addSelector(SELECTOR selector) {
        this.selectors.add(selector);
    }

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
