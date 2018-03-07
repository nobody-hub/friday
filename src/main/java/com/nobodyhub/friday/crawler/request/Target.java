package com.nobodyhub.friday.crawler.request;

import com.google.common.collect.Lists;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.jsoup.nodes.Document;

import java.util.List;
import java.util.regex.Pattern;

/**
 * Target of crawler to collect
 *
 * @author Ryan
 */
@Data
@EqualsAndHashCode
@ToString
public class Target {
    /**
     * the url pattern in which the target locates
     */
    protected String urlPattern;
    /**
     * how to select the interested element from the page
     * if empty, the whole contents that retured from url are the actual target
     */
    protected List<Selector> selectors;

    public boolean matches(String url) {
        if (url == null) {
            return false;
        }
        return Pattern.compile(urlPattern).matcher(url).find();
    }

    public List<Object> parseEOI(Document document) {
        if (selectors == null || selectors.isEmpty()) {
            return Lists.newArrayList(document);
        }
        List<Object> values = Lists.newArrayList();
        for (Selector selector : selectors) {
            values.addAll(selector.getAttrText(document));
        }
        return values;
    }
}
