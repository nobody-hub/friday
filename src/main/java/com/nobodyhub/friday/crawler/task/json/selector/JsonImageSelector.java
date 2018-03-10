package com.nobodyhub.friday.crawler.task.json.selector;

import com.nobodyhub.friday.crawler.task.common.ContentType;
import com.nobodyhub.friday.crawler.task.json.JsonSelector;

/**
 * @author Ryan
 */
public class JsonImageSelector extends JsonSelector {

    public JsonImageSelector(String urlPattern, String selector) {
        super(ContentType.IMAGE, urlPattern, selector);
    }
}
