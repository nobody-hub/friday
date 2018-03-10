package com.nobodyhub.friday.crawler.task.json;

import com.jayway.jsonpath.ReadContext;
import com.nobodyhub.friday.crawler.task.common.Task;
import com.nobodyhub.friday.crawler.task.common.TaskType;

import java.util.List;

/**
 * @author Ryan
 */
public class JsonTask
        extends Task<ReadContext, JsonSelector> {

    public JsonTask() {
        super(TaskType.JSON);
    }

    @Override
    public List<String> parseLink(ReadContext readContext) {
        //TODO
        return null;
    }
}
