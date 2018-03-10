package com.nobodyhub.friday.crawler.task.interest.json;


import com.jayway.jsonpath.ReadContext;
import com.nobodyhub.friday.crawler.task.interest.common.ContentType;
import com.nobodyhub.friday.crawler.task.interest.common.Interest;
import com.nobodyhub.friday.crawler.task.interest.common.Selector;

/**
 * @author Ryan
 */
public abstract class JsonInterest<SELECTOR extends Selector<ReadContext>>
        extends Interest<ReadContext, SELECTOR> {

    public JsonInterest(String urlPattern) {
        super(ContentType.JSON, urlPattern);
    }
}
