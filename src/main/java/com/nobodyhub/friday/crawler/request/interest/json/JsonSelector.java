package com.nobodyhub.friday.crawler.request.interest.json;

import com.jayway.jsonpath.ReadContext;
import com.nobodyhub.friday.crawler.request.interest.common.Selector;

import java.util.List;

/**
 * @author Ryan
 */
public class JsonSelector extends Selector<ReadContext, String> {

    public JsonSelector(String selector) {
        super(selector);
    }

    @Override
    public List<String> select(ReadContext document) {
        return document.read(selector);
    }
}
