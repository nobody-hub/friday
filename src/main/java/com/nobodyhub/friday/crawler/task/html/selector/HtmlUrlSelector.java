package com.nobodyhub.friday.crawler.task.html.selector;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.Lists;
import com.nobodyhub.friday.crawler.task.common.ContentType;
import com.nobodyhub.friday.crawler.task.html.HtmlSelector;
import lombok.EqualsAndHashCode;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.List;

/**
 * @author Ryan
 */
@EqualsAndHashCode(callSuper = true)
public class HtmlUrlSelector extends HtmlSelector {

    public HtmlUrlSelector(
            @JsonProperty("urlPattern") String urlPattern,
            @JsonProperty("selector") String selector) {
        super(ContentType.URL, urlPattern, selector);
    }

    @Override
    public List<String> select(Document document) {
        List<String> contents = Lists.newArrayList();
        Elements elements = document.select(selector);
        for (Element element : elements) {
            contents.add(element.absUrl("href"));
        }
        return contents;
    }


}
