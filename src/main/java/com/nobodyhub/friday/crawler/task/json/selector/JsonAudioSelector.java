package com.nobodyhub.friday.crawler.task.json.selector;

import com.nobodyhub.friday.crawler.task.common.ContentType;
import com.nobodyhub.friday.crawler.task.json.JsonSelector;

/**
 * @author Ryan
 */
public class JsonAudioSelector extends JsonSelector {

    public JsonAudioSelector(String urlPattern, String selector) {
        super(ContentType.AUDIO, urlPattern, selector);
    }
}
