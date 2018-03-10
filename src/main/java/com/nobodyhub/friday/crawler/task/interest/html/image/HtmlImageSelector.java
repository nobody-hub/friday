package com.nobodyhub.friday.crawler.task.interest.html.image;

import com.google.common.collect.Lists;
import com.nobodyhub.friday.crawler.task.interest.common.ContentType;
import com.nobodyhub.friday.crawler.task.interest.common.Selector;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.List;

/**
 * @author Ryan
 */
public class HtmlImageSelector extends Selector<Document> {

    public HtmlImageSelector(String selector) {
        super(ContentType.IMAGE, selector);
    }

    @Override
    public List<String> select(Document document) {
        List<String> contents = Lists.newArrayList();
        Elements elements = document.select(selector);
        for (Element element : elements) {
            element.absUrl("src");
        }
        return contents;
    }


}
