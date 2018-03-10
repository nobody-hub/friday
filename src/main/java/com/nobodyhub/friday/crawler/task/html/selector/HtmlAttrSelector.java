package com.nobodyhub.friday.crawler.task.html.selector;

import com.google.common.collect.Lists;
import com.nobodyhub.friday.crawler.task.common.ContentType;
import com.nobodyhub.friday.crawler.task.html.HtmlSelector;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.List;

/**
 * @author Ryan
 */
public class HtmlAttrSelector extends HtmlSelector {
    /**
     * intereted attr of the selected element
     */
    protected final List<String> attributes;

    public HtmlAttrSelector(String urlPattern, String selector) {
        super(ContentType.TEXT, urlPattern, selector);
        this.attributes = Lists.newArrayList();
    }

    @Override
    public List<String> select(Document document) {
        List<String> contents = Lists.newArrayList();
        Elements elements = document.select(selector);
        for (Element element : elements) {
            for (String attr : this.attributes) {
                contents.add(element.attr(attr));
            }
        }
        return contents;
    }


}
