package com.nobodyhub.friday.crawler.task.interest.html.attr;

import com.google.common.collect.Lists;
import com.nobodyhub.friday.crawler.task.interest.common.ContentType;
import com.nobodyhub.friday.crawler.task.interest.html.HtmlInterest;
import org.jsoup.nodes.Document;

import java.util.List;

/**
 * @author Ryan
 */
public class HtmlAttrInterest extends HtmlInterest<HtmlAttrSelector> {
    public HtmlAttrInterest(String urlPattern) {
        super(ContentType.TEXT, urlPattern);
    }

    @Override
    public List<String> fetch(Document document) {
        List<String> contents = Lists.newArrayList();
        for (HtmlAttrSelector selector : selectors) {
            contents.addAll(selector.select(document));
        }
        return contents;
    }
}
