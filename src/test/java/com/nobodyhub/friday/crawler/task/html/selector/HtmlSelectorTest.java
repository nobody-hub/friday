package com.nobodyhub.friday.crawler.task.html.selector;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import com.nobodyhub.friday.crawler.task.html.HtmlSelector;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Before;

import java.io.IOException;
import java.net.URISyntaxException;

/**
 * @author Ryan
 */
public abstract class HtmlSelectorTest {
    protected Document document;
    protected HtmlSelector selector;

    @Before
    public void setup() throws URISyntaxException, IOException {
        document = Jsoup.parse(Resources.toString(getClass().getResource("selector.html"), Charsets.UTF_8));
        document.setBaseUri("http://nobodyhub.com/selector/test");
    }
}
