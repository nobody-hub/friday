package com.nobodyhub.friday.crawler.request.interest.json;


import com.jayway.jsonpath.ReadContext;
import com.nobodyhub.friday.crawler.request.interest.common.ContentType;
import com.nobodyhub.friday.crawler.request.interest.common.Interest;
import com.nobodyhub.friday.crawler.request.interest.common.Selector;

/**
 * @author Ryan
 */
public abstract class JsonInterest<CONTENTTYPE, SELECTOR extends Selector<ReadContext, CONTENTTYPE>>
        extends Interest<CONTENTTYPE, ReadContext, SELECTOR> {

    public JsonInterest(String urlPattern) {
        super(ContentType.JSON, urlPattern);
    }
}
