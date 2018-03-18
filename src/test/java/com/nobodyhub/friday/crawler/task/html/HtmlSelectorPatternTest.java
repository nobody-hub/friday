package com.nobodyhub.friday.crawler.task.html;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Before;

import java.io.IOException;
import java.net.URISyntaxException;

/**
 * @author Ryan
 */
public abstract class HtmlSelectorPatternTest {
    protected Document document;
    protected HtmlSelectorPattern selector;

    @Before
    public void setup() throws URISyntaxException, IOException {
        document = Jsoup.parse(Resources.toString(getClass().getClassLoader().getResource("com/nobodyhub/friday/crawler/task/html/selector.html"), Charsets.UTF_8));
        document.setBaseUri("http://nobodyhub.com/selector/test");
    }
}
