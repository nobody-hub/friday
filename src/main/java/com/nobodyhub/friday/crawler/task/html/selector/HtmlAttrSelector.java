package com.nobodyhub.friday.crawler.task.html.selector;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.Lists;
import com.nobodyhub.friday.crawler.task.common.ContentType;
import com.nobodyhub.friday.crawler.task.html.HtmlSelector;
import lombok.EqualsAndHashCode;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.List;

/**
 * @author Ryan
 */
@EqualsAndHashCode(callSuper = true)
public class HtmlAttrSelector extends HtmlSelector {
    /**
     * intereted attr of the selected element
     * if empty, will get element.text()
     */
    @JsonProperty("attributes")
    protected final List<String> attributes;

    public HtmlAttrSelector(
            @JsonProperty("urlPattern") String urlPattern,
            @JsonProperty("selector") String selector) {
        super(ContentType.TEXT, urlPattern, selector);
        this.attributes = Lists.newArrayList();
    }

    @Override
    public List<String> select(Document document) {
        List<String> contents = Lists.newArrayList();
        Elements elements = document.select(selector);
        for (Element element : elements) {
            if (this.attributes.isEmpty()) {
                contents.add(element.text());
            } else {
                for (String attr : this.attributes) {
                    contents.add(element.attr(attr));
                }
            }
        }
        return contents;
    }

    public void addAttribute(String attribute) {
        this.attributes.add(attribute);
    }
}
