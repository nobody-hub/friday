package com.nobodyhub.friday.crawler.task.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.regex.Pattern;

/**
 * @author Ryan
 */
@RequiredArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
public abstract class SelectorPattern<DOCUMENT> {
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
     *
     * @see <a href="https://www.w3schools.com/cssref/css_selectors.asp">CSS Selector for HTML</a>
     * @see <a href="https://github.com/json-path/JsonPath">JsonPath for JSON</a>
     */
    @JsonProperty("selector")
    protected final String selector;

    /**
     * select the target contents from given {@link DOCUMENT}
     *
     * @param url url of current page
     * @param document the document to be parsed
     * @return
     */
    public SelectorResult select(String url, DOCUMENT document) {
        SelectorResult result = new SelectorResult(this.type, url, this.selector);
        select(document, result);
        return result;
    }

    /**
     * Fill the {@code Result} from parsing the {@code Document}
     *
     * @param document document to be parsed
     * @param result   the parse result
     * @return
     */
    protected abstract void select(DOCUMENT document, SelectorResult result);

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


