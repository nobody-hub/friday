package com.nobodyhub.friday.crawler.request;

import com.google.common.collect.Lists;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.List;

/**
 * Definition of a crawler task
 *
 * @author Ryan
 */
@Data
@EqualsAndHashCode
@ToString
public class Task {
    /**
     * Task Name
     */
    protected String name;
    /**
     * Task Description
     */
    protected String description;
    /**
     * Task Version
     */
    protected String version;
    /**
     * Task User-Agent
     */
    protected String userAgent;
    /**
     * Entrance urls of crawler
     */
    protected List<String> entranceUrls;
    /**
     * Links that will be traced by the crawler
     */
    protected List<Link> links;
    /**
     * crawler target in the link
     * urlPattern => target
     */
    protected List<Target> targets;

    public List<String> parseLink(Document doc) {
        List<String> urls = Lists.newArrayList();
        Elements aTags = doc.select("a[href]");
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

    public List<Object> parseTarget(Document doc) {
        List<Object> values = Lists.newArrayList();
        for (Target target : targets) {
            if (target.matches(doc.baseUri())) {
                values.addAll(target.parseEOI(doc));
            }
        }
        return values;
    }
}
