package com.nobodyhub.friday.crawler.task.interest.html;


import com.nobodyhub.friday.crawler.task.interest.common.ContentType;
import com.nobodyhub.friday.crawler.task.interest.common.Interest;
import com.nobodyhub.friday.crawler.task.interest.common.Selector;
import org.jsoup.nodes.Document;

/**
 * @author Ryan
 */
public abstract class HtmlInterest<SELECTOR extends Selector<Document>>
        extends Interest<Document, SELECTOR> {

    public HtmlInterest(ContentType type, String urlPattern) {
        super(type, urlPattern);
    }
}
