package com.nobodyhub.friday.crawler.request.interest.html.image;

import com.google.common.collect.Lists;
import com.nobodyhub.friday.crawler.request.interest.common.ContentType;
import com.nobodyhub.friday.crawler.request.interest.html.HtmlInterest;
import org.jsoup.nodes.Document;

import java.util.List;

/**
 * @author Ryan
 */
public class HtmlImageInterest extends HtmlInterest<String, HtmlImageSelector> {
    public HtmlImageInterest(String urlPattern) {
        super(ContentType.IMAGE, urlPattern);
    }

    @Override
    public List<String> fetch(Document document) {
        List<String> contents = Lists.newArrayList();
        for (HtmlImageSelector selector : selectors) {
            contents.addAll(selector.select(document));
        }
        return contents;
    }
}
