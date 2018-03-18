package com.nobodyhub.friday.crawler.task.html;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nobodyhub.friday.crawler.task.common.LinkPattern;
import com.nobodyhub.friday.crawler.task.common.Request;
import com.nobodyhub.friday.crawler.task.html.selector.HtmlUrlSelector;
import lombok.EqualsAndHashCode;
import org.jsoup.nodes.Document;

/**
 * @author Ryan
 */
@EqualsAndHashCode(callSuper = true)
public class HtmlLinkPattern extends LinkPattern<Document, HtmlUrlSelector> {
    public HtmlLinkPattern(
            @JsonProperty("urlPattern") String urlPattern,
            @JsonProperty("selector") HtmlUrlSelector selector,
            @JsonProperty("request") Request request) {
        super(urlPattern, selector, request);
    }
}
