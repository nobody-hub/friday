package com.nobodyhub.friday.crawler.task.interest.json;

import com.jayway.jsonpath.ReadContext;
import com.nobodyhub.friday.crawler.task.interest.common.ContentType;
import com.nobodyhub.friday.crawler.task.interest.common.Selector;

import java.util.List;

/**
 * @author Ryan
 */
public class JsonSelector extends Selector<ReadContext, String> {

    public JsonSelector(String selector) {
        super(ContentType.JSON, selector);
    }

    @Override
    public List<String> select(ReadContext document) {
        return document.read(selector);
    }
}
