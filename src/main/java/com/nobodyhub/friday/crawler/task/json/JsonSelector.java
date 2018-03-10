package com.nobodyhub.friday.crawler.task.json;

import com.jayway.jsonpath.ReadContext;
import com.nobodyhub.friday.crawler.task.common.ContentType;
import com.nobodyhub.friday.crawler.task.common.Selector;

import java.util.List;

/**
 * @author Ryan
 */
public abstract class JsonSelector extends Selector<ReadContext> {

    public JsonSelector(ContentType type, String urlPattern, String selector) {
        super(type, urlPattern, selector);
    }

    @Override
    public List<String> select(ReadContext document) {
        return document.read(selPath);
    }
}
