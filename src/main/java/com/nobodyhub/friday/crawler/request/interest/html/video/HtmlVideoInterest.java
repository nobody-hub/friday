package com.nobodyhub.friday.crawler.request.interest.html.video;

import com.google.common.collect.Lists;
import com.nobodyhub.friday.crawler.request.interest.common.ContentType;
import com.nobodyhub.friday.crawler.request.interest.html.HtmlInterest;
import org.jsoup.nodes.Document;

import java.util.List;

/**
 * @author Ryan
 */
public class HtmlVideoInterest extends HtmlInterest<String, HtmlVideoSelector> {
    public HtmlVideoInterest(String urlPattern) {
        super(ContentType.VIDEO, urlPattern);
    }

    @Override
    public List<String> fetch(Document document) {
        List<String> contents = Lists.newArrayList();
        for (HtmlVideoSelector selector : selectors) {
            contents.addAll(selector.select(document));
        }
        return contents;
    }
}
