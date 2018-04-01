package com.nobodyhub.friday.crawler.core.definition.html.selector;

import com.google.common.collect.Lists;
import com.nobodyhub.friday.crawler.core.definition.common.item.Item;
import com.nobodyhub.friday.crawler.core.definition.html.HtmlItemPatternTest;
import org.junit.Test;

import java.util.List;

import static com.nobodyhub.friday.crawler.core.definition.common.item.Item.HTML_SRC;
import static org.junit.Assert.assertEquals;

/**
 * @author Ryan
 */
public class HtmlVideoItemPatternTest extends HtmlItemPatternTest {
    @Test
    public void testSelect() {
        this.selector = new HtmlVideoItemPattern("urlPattern", Lists.newArrayList("video.selected"));
        List<Item> contents = this.selector.select("http://nobodyhub.com/", content);
        assertEquals(2, contents.size());
        List<String> values = convertToAttrList(contents, HTML_SRC);
        assertEquals(true, values.contains("http://nobodyhub.com/selector/movie.mp4"));
        assertEquals(true, values.contains("http://nobodyhub.com/selector/movie.ogg"));
    }
}