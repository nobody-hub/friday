package com.nobodyhub.friday.crawler.task.html;

import com.google.common.collect.Lists;
import com.nobodyhub.friday.crawler.task.common.Link;
import com.nobodyhub.friday.crawler.task.common.Task;
import com.nobodyhub.friday.crawler.task.common.TaskType;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.List;

/**
 * Task definition to parse HTML contents
 *
 * @author Ryan
 */
public class HtmlTask
        extends Task<Document, HtmlSelector> {

    public HtmlTask() {
        super(TaskType.HTML);
    }

    @Override
    public List<String> parseLink(Document document) {
        List<String> urls = Lists.newArrayList();
        Elements aTags = document.select("a[href]");
        for (Element aTag : aTags) {
            String url = aTag.absUrl("href");
            for (Link link : links) {
                if (link.matches(url)) {
                    urls.add(url);
                }
            }
        }
        return urls;
    }
}
