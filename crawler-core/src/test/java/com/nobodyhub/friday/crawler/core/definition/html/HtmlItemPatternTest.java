package com.nobodyhub.friday.crawler.core.definition.html;

import com.google.common.base.Charsets;
import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.google.common.io.Resources;
import com.nobodyhub.friday.crawler.core.definition.common.item.Item;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Before;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import javax.annotation.Nullable;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

/**
 * @author Ryan
 */
public abstract class HtmlItemPatternTest {
    protected Document document;
    protected HtmlItemPattern selector;
    @Mock
    protected HtmlLinkContent content;

    @Before
    public void setup() throws URISyntaxException, IOException {
        MockitoAnnotations.initMocks(this);

        document = Jsoup.parse(Resources.toString(getClass().getClassLoader().getResource("com/nobodyhub/friday/crawler/core/definition/html/selector.html"), Charsets.UTF_8));
        document.setBaseUri("http://nobodyhub.com/selector/test");

        Mockito.when(content.getDocument()).thenReturn(document);
    }

    protected List<String> convertToAttrList(List<Item> results, final String attr) {
        return Lists.transform(results, new Function<Item, String>() {
            @Nullable
            @Override
            public String apply(@Nullable Item input) {
                return input.getAttr(attr);
            }
        });
    }
}
