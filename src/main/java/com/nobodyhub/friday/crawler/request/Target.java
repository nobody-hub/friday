package com.nobodyhub.friday.crawler.request;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

/**
 * Target of crawler to collect
 *
 * @author Ryan
 */
@Data
@EqualsAndHashCode
@ToString
public class Target {
    /**
     * the url pattern in which the target locates
     */
    protected String urlPattern;
    /**
     * how to select the interested element from the page
     * if empty, the whole contents that retured from url are the actual target
     */
    protected List<Selector> selectors;

}
