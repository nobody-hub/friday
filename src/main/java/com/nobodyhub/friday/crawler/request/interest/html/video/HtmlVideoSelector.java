package com.nobodyhub.friday.crawler.request.interest.html.video;

import com.google.common.collect.Lists;
import com.nobodyhub.friday.crawler.request.interest.common.ContentType;
import com.nobodyhub.friday.crawler.request.interest.common.Selector;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.List;

/**
 * @author Ryan
 */
public class HtmlVideoSelector extends Selector<Document, String> {

    public HtmlVideoSelector(String selector) {
        super(ContentType.VIDEO, selector);
    }

    @Override
    public List<String> select(Document document) {
        List<String> contents = Lists.newArrayList();
        Elements elements = document.select(selector + " source");
        for (Element element : elements) {
            element.absUrl("src");
        }
        return contents;
    }


}
