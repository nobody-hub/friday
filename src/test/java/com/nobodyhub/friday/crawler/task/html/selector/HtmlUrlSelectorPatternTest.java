package com.nobodyhub.friday.crawler.task.html.selector;

import com.nobodyhub.friday.crawler.task.html.HtmlSelectorPatternTest;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @author Ryan
 */
public class HtmlUrlSelectorPatternTest extends HtmlSelectorPatternTest {
    @Test
    public void testSelect() {
        this.selector = new HtmlUrlSelectorPattern("urlPattern", "a.mnav");
        List<String> contents = this.selector.select(document);
        assertEquals(6, contents.size());
        assertEquals(true, contents.contains("http://news.baidu.com"));
        assertEquals(true, contents.contains("http://www.hao123.com"));
        assertEquals(true, contents.contains("http://map.baidu.com"));
        assertEquals(true, contents.contains("http://v.baidu.com"));
        assertEquals(true, contents.contains("http://tieba.baidu.com"));
        assertEquals(true, contents.contains("http://xueshu.baidu.com"));
    }
}