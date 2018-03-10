package com.nobodyhub.friday.crawler.task.html;

import com.nobodyhub.friday.crawler.task.common.ContentType;
import com.nobodyhub.friday.crawler.task.common.Selector;
import org.jsoup.nodes.Document;

/**
 * @author Ryan
 */
public abstract class HtmlSelector extends Selector<Document> {

    public HtmlSelector(ContentType type, String urlPattern, String selector) {
        super(type, urlPattern, selector);
    }
}
