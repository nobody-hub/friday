package com.nobodyhub.friday.crawler.task.json.selector;

import com.google.common.collect.Lists;
import com.nobodyhub.friday.crawler.task.common.ContentType;
import com.nobodyhub.friday.crawler.task.json.JsonSelector;

import java.util.List;

/**
 * @author Ryan
 */
public class JsonAttrSelector extends JsonSelector {
    /**
     * intereted attr of the selected element
     */
    protected final List<String> attributes;

    public JsonAttrSelector(String urlPattern, String selector) {
        super(ContentType.TEXT, urlPattern, selector);
        this.attributes = Lists.newArrayList();
    }
}
