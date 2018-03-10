package com.nobodyhub.friday.crawler.task.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.List;
import java.util.regex.Pattern;

/**
 * @author Ryan
 */
@RequiredArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
public abstract class Selector<DOCUMENT> {
    /**
     * Type of content
     */
    @JsonProperty("type")
    protected final ContentType type;
    /**
     * Url REGEX pattern that restrict the target
     */
    @JsonProperty("urlPattern")
    protected final String urlPattern;
    /**
     * the selector path
     */
    @JsonProperty("selector")
    protected final String selector;

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
        return url != null && Pattern.compile(urlPattern).matcher(url).find();
    }
}


