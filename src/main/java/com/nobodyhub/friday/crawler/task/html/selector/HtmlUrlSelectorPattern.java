package com.nobodyhub.friday.crawler.task.html.selector;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nobodyhub.friday.crawler.task.common.ContentType;
import com.nobodyhub.friday.crawler.task.common.SelectorResult;
import com.nobodyhub.friday.crawler.task.html.HtmlSelectorPattern;
import lombok.EqualsAndHashCode;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import static com.nobodyhub.friday.crawler.task.common.SelectorResult.HTML_HREF;

/**
 * @author Ryan
 */
@EqualsAndHashCode(callSuper = true)
public class HtmlUrlSelectorPattern extends HtmlSelectorPattern {

    public HtmlUrlSelectorPattern(
            @JsonProperty("urlPattern") String urlPattern,
            @JsonProperty("selector") String selector) {
        super(ContentType.URL, urlPattern, selector);
    }

    /**
     * @param document document to be parsed
     * @param result   the parse result
     * @see org.jsoup.nodes.Node#absUrl(String)
     */
    @Override
    protected void select(Document document, SelectorResult result) {
        Elements elements = document.select(selector);
        for (Element element : elements) {
            result.addAttr(HTML_HREF, element.absUrl(HTML_HREF));
        }
    }
}
