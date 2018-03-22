package com.nobodyhub.friday.crawler.core.definition.common.link;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author Ryan
 */
@RequiredArgsConstructor
@Getter
public class Link {
    /**
     * url of the link
     * <p>
     */
    @JsonProperty("url")
    protected final String url;

    /**
     * how to connet the link
     */
    @JsonProperty("request")
    protected final Request request;
}
