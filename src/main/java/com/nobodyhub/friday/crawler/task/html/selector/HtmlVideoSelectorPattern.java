package com.nobodyhub.friday.crawler.task.html.selector;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.Lists;
import com.nobodyhub.friday.crawler.task.common.ContentType;
import com.nobodyhub.friday.crawler.task.common.SelectorResult;
import com.nobodyhub.friday.crawler.task.html.HtmlSelectorPattern;
import lombok.EqualsAndHashCode;
import org.jsoup.helper.StringUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.List;

import static com.nobodyhub.friday.crawler.task.common.SelectorResult.HTML_SRC;

/**
 * @author Ryan
 */
@EqualsAndHashCode(callSuper = true)
public class HtmlVideoSelectorPattern extends HtmlSelectorPattern {

    public HtmlVideoSelectorPattern(
            @JsonProperty("urlPattern") String urlPattern,
            @JsonProperty("selectors") List<String> selectors) {
        super(ContentType.VIDEO, urlPattern, selectors);
    }

    /**
     * @param url
     * @param document
     * @param selector
     * @return
     * @see org.jsoup.nodes.Node#absUrl(String)
     */
    @Override
    protected List<SelectorResult> select(String url, Document document, String selector) {
        List<SelectorResult> results = Lists.newArrayList();
        Elements elements = document.select(selector);
        for (Element element : elements) {
            SelectorResult result = new SelectorResult(this.type, url, selector);
            result.addAttr(HTML_SRC, StringUtil.resolve(element.baseUri(), element.attr(HTML_SRC)));
        }
        return results;
    }
}
