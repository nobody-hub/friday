package com.nobodyhub.friday.crawler.task.html.selector;

import com.google.common.collect.Lists;
import com.nobodyhub.friday.crawler.task.common.item.Item;
import com.nobodyhub.friday.crawler.task.html.HtmlItemPatternTest;
import org.junit.Test;

import java.util.List;

import static com.nobodyhub.friday.crawler.task.common.item.Item.HTML_HREF;
import static org.junit.Assert.assertEquals;

/**
 * @author Ryan
 */
public class HtmlUrlItemPatternTest extends HtmlItemPatternTest {
    @Test
    public void testSelect() {
        this.selector = new HtmlUrlItemPattern("urlPattern", Lists.newArrayList("a.mnav"));
        List<Item> contents = this.selector.select("http://www.baidu.com/", document);
        assertEquals(6, contents.size());
        List<String> values = convertToAttrList(contents, HTML_HREF);
        assertEquals(true, values.contains("http://news.baidu.com"));
        assertEquals(true, values.contains("http://www.hao123.com"));
        assertEquals(true, values.contains("http://map.baidu.com"));
        assertEquals(true, values.contains("http://v.baidu.com"));
        assertEquals(true, values.contains("http://tieba.baidu.com"));
        assertEquals(true, values.contains("http://xueshu.baidu.com"));
    }
}