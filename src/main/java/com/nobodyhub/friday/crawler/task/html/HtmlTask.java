package com.nobodyhub.friday.crawler.task.html;

import com.nobodyhub.friday.crawler.task.common.Task;
import com.nobodyhub.friday.crawler.task.common.TaskType;
import org.jsoup.nodes.Document;

/**
 * Task definition to parse HTML contents
 *
 * @author Ryan
 */
public class HtmlTask
        extends Task<Document, HtmlSelector, HtmlLink> {

    public HtmlTask() {
        super(TaskType.HTML);
    }
}
