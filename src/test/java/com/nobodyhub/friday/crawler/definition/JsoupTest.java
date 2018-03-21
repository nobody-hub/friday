package com.nobodyhub.friday.crawler.definition;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * @author Ryan
 */
public class JsoupTest {
    private static final Logger logger = LoggerFactory.getLogger(JsoupTest.class);

    @Test
    public void test() throws IOException {
        Document doc = Jsoup.connect("http://en.wikipedia.org/").get();
        logger.info("Page Title:{}", doc.title());
        Elements newsHeadlines = doc.select("#mp-itn b a");
        for (Element headline : newsHeadlines) {
            logger.info("title:{}, href:{}", headline.attr("title"), headline.absUrl("href"));
        }
    }
}