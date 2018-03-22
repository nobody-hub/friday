package com.nobodyhub.friday.crawler.core.definition.html;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.Lists;
import com.nobodyhub.friday.crawler.core.definition.common.item.Item;
import com.nobodyhub.friday.crawler.core.definition.common.link.Link;
import com.nobodyhub.friday.crawler.core.definition.common.task.Task;
import com.nobodyhub.friday.crawler.core.definition.common.task.TaskType;
import lombok.EqualsAndHashCode;
import org.jsoup.Connection;
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
    public void execute(Link startLink, List<Link> links, List<Item> items) throws IOException {
        Connection.Response response = connect(startLink);
        Document document = response.parse();
        links.addAll(parseLink(startLink, document, response));
        items.addAll(parseContent(startLink.getUrl(), document));
    }

    @Override
    public Document extract(Connection.Response response) throws IOException {
        return response.parse();
    }
}
