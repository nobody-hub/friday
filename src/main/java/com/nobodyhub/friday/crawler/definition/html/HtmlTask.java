package com.nobodyhub.friday.crawler.definition.html;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.Lists;
import com.nobodyhub.friday.crawler.definition.common.link.Link;
import com.nobodyhub.friday.crawler.definition.common.task.Task;
import com.nobodyhub.friday.crawler.definition.common.task.TaskType;
import lombok.EqualsAndHashCode;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.List;

/**
 * Task definition to parse HTML contents
 *
 * @author Ryan
 */
@EqualsAndHashCode(callSuper = true)
public class HtmlTask
        extends Task<Document, HtmlItemPattern, HtmlLinkPattern> {
    public HtmlTask(
            String name,
            String description,
            String version,
            String userAgent) {
        super(TaskType.HTML,
                name,
                description,
                version,
                userAgent,
                Lists.newArrayList(),
                Lists.newArrayList(),
                Lists.newArrayList());
    }

    @JsonCreator
    public HtmlTask(
            @JsonProperty("name") String name,
            @JsonProperty("description") String description,
            @JsonProperty("version") String version,
            @JsonProperty("userAgent") String userAgent,
            @JsonProperty("entranceUrls") List<Link> entranceUrls,
            @JsonProperty("htmlLinks") List<HtmlLinkPattern> htmlLinks,
            @JsonProperty("htmlSelectors") List<HtmlItemPattern> htmlSelectors) {
        super(TaskType.HTML,
                name,
                description,
                version,
                userAgent,
                entranceUrls,
                htmlLinks,
                htmlSelectors);
    }

    @Override
    public Document connect(Link link) throws IOException {
        return link.getRequest().execute(link.getUrl()).parse();
    }
}
