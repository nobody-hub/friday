package com.nobodyhub.friday.crawler.task.json.selector;

import com.nobodyhub.friday.crawler.task.common.ContentType;
import com.nobodyhub.friday.crawler.task.json.JsonSelector;

/**
 * @author Ryan
 */
public class JsonVideoSelector extends JsonSelector {

    public JsonVideoSelector(String urlPattern, String selector) {
        super(ContentType.VIDEO, urlPattern, selector);
    }
}
