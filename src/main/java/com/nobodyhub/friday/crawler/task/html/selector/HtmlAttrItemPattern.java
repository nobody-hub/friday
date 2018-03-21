package com.nobodyhub.friday.crawler.task.html.selector;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.Lists;
import com.nobodyhub.friday.crawler.task.common.item.Item;
import com.nobodyhub.friday.crawler.task.common.item.ItemType;
import com.nobodyhub.friday.crawler.task.html.HtmlItemPattern;
import lombok.EqualsAndHashCode;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.List;

import static com.nobodyhub.friday.crawler.task.common.item.Item.HTML_TEXT;

/**
 * @author Ryan
 */
@EqualsAndHashCode(callSuper = true)
public class HtmlAttrItemPattern extends HtmlItemPattern {
    /**
     * intereted attr of the selected element
     * if empty, will get element.text()
     */
    @JsonProperty("attributes")
    protected final List<String> attributes;

    public HtmlAttrItemPattern(
            @JsonProperty("urlPattern") String urlPattern,
            @JsonProperty("selectors") List<String> selectors) {
        super(ItemType.TEXT, urlPattern, selectors);
        this.attributes = Lists.newArrayList();
    }

    @Override
    protected List<Item> select(String url, Document document, String selector) {
        List<Item> results = Lists.newArrayList();
        Elements elements = document.select(selector);
        for (Element element : elements) {
            Item result = new Item(this.type, url, selector);
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
