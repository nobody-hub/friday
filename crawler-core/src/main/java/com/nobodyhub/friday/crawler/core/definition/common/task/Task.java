package com.nobodyhub.friday.crawler.core.definition.common.task;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.google.common.collect.Lists;
import com.nobodyhub.friday.crawler.core.definition.common.item.Item;
import com.nobodyhub.friday.crawler.core.definition.common.item.ItemPattern;
import com.nobodyhub.friday.crawler.core.definition.common.link.Link;
import com.nobodyhub.friday.crawler.core.definition.common.link.LinkContent;
import com.nobodyhub.friday.crawler.core.definition.common.link.LinkPattern;
import com.nobodyhub.friday.crawler.core.definition.common.link.Request;
import com.nobodyhub.friday.crawler.core.definition.html.HtmlTask;
import com.nobodyhub.friday.crawler.core.definition.json.JsonTask;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.jsoup.Connection;

import java.io.IOException;
import java.math.BigInteger;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Definition of a crawler task
 *
 * @author Ryan
 */
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.EXISTING_PROPERTY,
        property = "taskType",
        visible = true
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = HtmlTask.class, name = "HTML"),
        @JsonSubTypes.Type(value = JsonTask.class, name = "JSON")
})
@Data
@EqualsAndHashCode
@ToString
@RequiredArgsConstructor
public abstract class Task<
        LINKCONTENT extends LinkContent,
        SELECTOR extends ItemPattern<LINKCONTENT>,
        LINKPATTERN extends LinkPattern<LINKCONTENT, ? extends SELECTOR>> {
    /**
     * Type of task
     */
    @JsonProperty("taskType")
    protected final TaskType taskType;
    /**
     * Task Name
     */
    @JsonProperty("name")
    protected final String name;
    /**
     * Task Description
     */
    @JsonProperty("description")
    protected final String description;
    /**
     * Task Version
     */
    @JsonProperty("version")
    protected final String version;
    /**
     * Task User-Agent
     */
    @JsonProperty("userAgent")
    protected final String userAgent;
    /**
     * Entrance urls of crawler
     */
    @JsonProperty("entranceUrls")
    protected final List<Link> entranceUrls;
    /**
     * Links that will be traced by the crawler
     */
    @JsonProperty("links")
    protected final List<LINKPATTERN> links;
    /**
     * selectors to select interested contents
     */
    @JsonProperty("selectors")
    protected final List<SELECTOR> selectors;

    /**
     * the limits of number of {@link Link}s to be processed
     */
    protected BigInteger limit = BigInteger.valueOf(-1L);
    protected AtomicReference<BigInteger> limitCount = new AtomicReference<>(BigInteger.ZERO);

    /**
     * Get futher links
     *
     * @param content
     * @return
     */
    public List<Link> parseLink(LINKCONTENT content) {
        Link link = content.getLink();

        //parse <a>
        List<String> urls = Lists.newArrayList();
        String baseUrl = link.getUrl();
        for (LINKPATTERN pattern : links) {
            if (pattern.matches(baseUrl)) {
                urls.addAll(pattern.parse(link.getUrl(), content));
            }
        }
        List<Link> newLinks = Lists.newArrayList();
        for (String url : urls) {
            newLinks.add(new Link(url, new Request(link.getRequest())));
        }
        return newLinks;
    }

    /**
     * Get contents of selectors
     *
     * @param content
     * @return
     */
    public List<Item> parseContent(LINKCONTENT content) {
        String url = content.getLink().getUrl();
        List<Item> values = Lists.newArrayList();
        for (SELECTOR pattern : selectors) {
            if (pattern.matches(url)) {
                values.addAll(pattern.select(url, content));
            }
        }
        return values;
    }

    public void addEntranceUrl(Link link) {
        this.entranceUrls.add(link);
    }

    /**
     * add selector to select interested contents
     *
     * @param selector
     */
    public void addSelector(SELECTOR selector) {
        this.selectors.add(selector);
    }

    /**
     * add link to fetch more contents
     *
     * @param link
     */
    public void addLink(LINKPATTERN link) {
        this.links.add(link);
    }

    /**
     * Read response from {@link Link}
     *
     * @param link
     * @return
     * @throws IOException
     */
    public abstract LINKCONTENT connect(Link link) throws IOException;

    protected Connection.Response request(Link link) throws IOException {
        return link.getRequest().execute(link.getUrl());
    }


    protected void increaseCount() {
        this.limitCount.getAndAccumulate(BigInteger.ONE, (previous, toAdd) -> previous.add(toAdd));
    }

    @JsonIgnore
    public boolean isFinish() {
        return limit.compareTo(BigInteger.valueOf(-1L)) != 0
                && limit.compareTo(limitCount.get()) <= 0;
    }
}
