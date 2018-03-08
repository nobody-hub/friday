package com.nobodyhub.friday.crawler.request.interest.html.audio;

import com.google.common.collect.Lists;
import com.nobodyhub.friday.crawler.request.interest.common.Selector;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.List;

/**
 * @author Ryan
 */
public class HtmlAudioSelector extends Selector<Document, String> {

    public HtmlAudioSelector(String selector) {
        super(selector);
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
