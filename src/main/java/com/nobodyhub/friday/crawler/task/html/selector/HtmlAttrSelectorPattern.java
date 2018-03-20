package com.nobodyhub.friday.crawler.task.html.selector;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.Lists;
import com.nobodyhub.friday.crawler.task.common.ContentType;
import com.nobodyhub.friday.crawler.task.common.SelectorResult;
import com.nobodyhub.friday.crawler.task.html.HtmlSelectorPattern;
import lombok.EqualsAndHashCode;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.List;

import static com.nobodyhub.friday.crawler.task.common.SelectorResult.HTML_TEXT;

/**
 * @author Ryan
 */
@EqualsAndHashCode(callSuper = true)
public class HtmlAttrSelectorPattern extends HtmlSelectorPattern {
    /**
     * intereted attr of the selected element
     * if empty, will get element.text()
     */
    @JsonProperty("attributes")
    protected final List<String> attributes;

    public HtmlAttrSelectorPattern(
            @JsonProperty("urlPattern") String urlPattern,
            @JsonProperty("selectors") List<String> selectors) {
        super(ContentType.TEXT, urlPattern, selectors);
        this.attributes = Lists.newArrayList();
    }

    @Override
    protected List<SelectorResult> select(String url, Document document, String selector) {
        List<SelectorResult> results = Lists.newArrayList();
        Elements elements = document.select(selector);
        for (Element element : elements) {
            SelectorResult result = new SelectorResult(this.type, url, selector);
            if (this.attributes.isEmpty()) {
                result.addAttr(HTML_TEXT, element.text());
            } else {
                for (String attr : this.attributes) {
                    result.addAttr(attr, element.attr(attr));
                }
            }
            results.add(result);
        }
        return results;
    }

    public void addAttribute(String attribute) {
        this.attributes.add(attribute);
    }
}
