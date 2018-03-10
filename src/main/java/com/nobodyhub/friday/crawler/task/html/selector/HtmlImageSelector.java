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
public class HtmlImageSelector extends HtmlSelector {

    public HtmlImageSelector(String urlPattern, String selector) {
        super(ContentType.IMAGE, urlPattern, selector);
    }

    @Override
    public List<String> select(Document document) {
        List<String> contents = Lists.newArrayList();
        Elements elements = document.select(selPath);
        for (Element element : elements) {
            element.absUrl("src");
        }
        return contents;
    }


}
