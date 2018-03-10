package com.nobodyhub.friday.crawler.task.html.selector;

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
public abstract class HtmlSelectorTest {
    protected Document document;

    @Before
    public void setup() throws URISyntaxException, IOException {
        document = Jsoup.parse(Resources.toString(getClass().getResource("baidu.html"), Charsets.UTF_8));
    }
}
