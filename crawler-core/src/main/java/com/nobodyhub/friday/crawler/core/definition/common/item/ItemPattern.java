package com.nobodyhub.friday.crawler.core.definition.common.item;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.Lists;
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
public abstract class ItemPattern<DOCUMENT> {
    /**
     * Type of content
     */
    @JsonProperty("type")
    protected final ItemType type;
    /**
     * Url REGEX pattern that restrict the target
     */
    @JsonProperty("urlPattern")
    protected final String urlPattern;
    /**
     * the selector path
     *
     * @see <a href="https://www.w3schools.com/cssref/css_selectors.asp">CSS Selector for HTML</a>
     * @see <a href="https://github.com/json-path/JsonPath">JsonPath for JSON</a>
     */
    @JsonProperty("selectors")
    protected final List<String> selectors;

    /**
     * select the target contents from given {@link DOCUMENT}
     *
     * @param url      url of current page
     * @param document the document to be parsed
     * @return
     */
    public List<Item> select(String url, DOCUMENT document) {
        List<Item> results = Lists.newArrayList();
        for (String selector : selectors) {
            results.addAll(select(url, document, selector));
        }
        return results;
    }

    /**
     * select the result for each selector
     *
     * @param url
     * @param document
     * @param selector
     * @return
     */
    protected abstract List<Item> select(String url, DOCUMENT document, String selector);

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


