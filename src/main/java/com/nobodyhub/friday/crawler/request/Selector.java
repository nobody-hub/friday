package com.nobodyhub.friday.crawler.request;

/**
 * Selector to locate element on page
 *
 * @author Ryan
 */
public class Selector {
    protected SelectorType type;
    protected String sel;
}

/**
 * Type of Selector
 */
enum SelectorType {
    CSS,
    XPATH,
    JSON
}
