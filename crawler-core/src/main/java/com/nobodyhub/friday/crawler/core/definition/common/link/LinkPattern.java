package com.nobodyhub.friday.crawler.core.definition.common.link;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.Lists;
import com.nobodyhub.friday.crawler.core.definition.common.item.Item;
import com.nobodyhub.friday.crawler.core.definition.common.item.ItemPattern;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.List;
import java.util.regex.Pattern;

/**
 * Links that will be traced by the crawler
 * TODO: add JsonTypeInfo
 *
 * @author Ryan
 */
@Data
@EqualsAndHashCode
@ToString
@RequiredArgsConstructor
public abstract class LinkPattern<DOCUMENT, SELECTOR extends ItemPattern<DOCUMENT>> {
    /**
     * url pattern that link need match
     * <p>
     * <b>Note:</b>the {@link SELECTOR#urlPattern} will be ignored
     */
    @JsonProperty("urlPattern")
    protected final String urlPattern;

    /**
     * the selector to get the link
     */
    @JsonProperty("selector")
    protected final SELECTOR selector;

    /**
     * how to connet the link
     */
    @JsonProperty("request")
    protected final Request request;

    /**
     * whether the given url will be used as a future link or not
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

    /**
     * get the links for parsing contents
     *
     * @param url
     * @param document
     * @return
     */
    public List<String> parse(String url, DOCUMENT document) {
        List<Item> results = selector.select(url, document);
        List<String> urls = Lists.newArrayList();
        for (Item result : results) {
            urls.addAll(result.getAttributeMap().values());
        }
        return urls;
    }

}
