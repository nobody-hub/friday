package com.nobodyhub.friday.crawler.request.interest.html.audio;

import com.google.common.collect.Lists;
import com.nobodyhub.friday.crawler.request.interest.common.ContentType;
import com.nobodyhub.friday.crawler.request.interest.html.HtmlInterest;
import org.jsoup.nodes.Document;

import java.util.List;

/**
 * @author Ryan
 */
public class HtmlAudioInterest extends HtmlInterest<String, HtmlAudioSelector> {
    public HtmlAudioInterest(String urlPattern) {
        super(ContentType.AUDIO, urlPattern);
    }

    @Override
    public List<String> fetch(Document document) {
        List<String> contents = Lists.newArrayList();
        for (HtmlAudioSelector selector : selectors) {
            contents.addAll(selector.select(document));
        }
        return contents;
    }
}
