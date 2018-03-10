package com.nobodyhub.friday.crawler.task.json;

import com.jayway.jsonpath.ReadContext;
import com.nobodyhub.friday.crawler.task.common.Task;
import com.nobodyhub.friday.crawler.task.common.TaskType;

/**
 * @author Ryan
 */
public class JsonTask
        extends Task<ReadContext, JsonSelector, JsonLink> {

    public JsonTask() {
        super(TaskType.JSON);
    }
}
