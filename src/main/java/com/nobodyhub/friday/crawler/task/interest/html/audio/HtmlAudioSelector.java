package com.nobodyhub.friday.crawler.task.interest.html.audio;

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
public class HtmlAudioSelector extends Selector<Document> {

    public HtmlAudioSelector(String selector) {
        super(ContentType.AUDIO, selector);
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
