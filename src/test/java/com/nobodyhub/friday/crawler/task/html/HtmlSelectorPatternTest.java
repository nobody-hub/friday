package com.nobodyhub.friday.crawler.task.html;

import com.google.common.base.Charsets;
import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.google.common.io.Resources;
import com.nobodyhub.friday.crawler.task.common.SelectorResult;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Before;

import javax.annotation.Nullable;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

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

    protected List<String> convertToAttrList(List<SelectorResult> results, final String attr) {
        return Lists.transform(results, new Function<SelectorResult, String>() {
            @Nullable
            @Override
            public String apply(@Nullable SelectorResult input) {
                return input.getAttr(attr);
            }
        });
    }
}
