package com.nobodyhub.friday.crawler.task.html;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nobodyhub.friday.crawler.task.common.link.LinkPattern;
import com.nobodyhub.friday.crawler.task.common.link.Request;
import com.nobodyhub.friday.crawler.task.html.selector.HtmlUrlItemPattern;
import lombok.EqualsAndHashCode;
import org.jsoup.nodes.Document;

/**
 * @author Ryan
 */
@EqualsAndHashCode(callSuper = true)
public class HtmlLinkPattern extends LinkPattern<Document, HtmlUrlItemPattern> {
    public HtmlLinkPattern(
            @JsonProperty("urlPattern") String urlPattern,
            @JsonProperty("selector") HtmlUrlItemPattern selector,
            @JsonProperty("request") Request request) {
        super(urlPattern, selector, request);
    }
}
