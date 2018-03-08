package com.nobodyhub.friday.crawler.request;

import com.google.common.collect.Lists;
import com.nobodyhub.friday.crawler.request.interest.common.Interest;
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
     * crawler interest in the link
     */
    protected List<Interest> interests;

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
        for (Interest target : interests) {
            if (target.matches(doc.baseUri())) {
                values.addAll(target.fetch(doc));
            }
        }
        return values;
    }
}
