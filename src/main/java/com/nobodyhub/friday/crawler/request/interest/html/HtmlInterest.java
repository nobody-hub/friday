package com.nobodyhub.friday.crawler.request.interest.html;


import com.nobodyhub.friday.crawler.request.interest.common.Interest;
import com.nobodyhub.friday.crawler.request.interest.common.InterestType;
import com.nobodyhub.friday.crawler.request.interest.common.Selector;
import org.jsoup.nodes.Document;

/**
 * @author Ryan
 */
public abstract class HtmlInterest<CONTENTTYPE, SELECTOR extends Selector<Document, CONTENTTYPE>>
        extends Interest<CONTENTTYPE, Document, SELECTOR> {

    public HtmlInterest(InterestType type, String urlPattern) {
        super(type, urlPattern);
    }
}
