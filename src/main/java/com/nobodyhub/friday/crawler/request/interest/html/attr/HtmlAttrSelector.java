package com.nobodyhub.friday.crawler.request.interest.html.attr;

import com.google.common.collect.Lists;
import com.nobodyhub.friday.crawler.request.interest.common.Selector;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.List;

/**
 * @author Ryan
 */
public class HtmlAttrSelector extends Selector<Document, String> {
    /**
     * intereted attr of the selected element
     */
    protected final List<String> attributes;

    public HtmlAttrSelector(String selector) {
        super(selector);
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
