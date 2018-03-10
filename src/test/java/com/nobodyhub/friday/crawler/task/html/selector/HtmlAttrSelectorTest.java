package com.nobodyhub.friday.crawler.task.html.selector;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @author Ryan
 */
public class HtmlAttrSelectorTest extends HtmlSelectorTest {

    @Test
    public void testSelect() {
        HtmlAttrSelector selector = new HtmlAttrSelector("urlPattern", "a.mnav");
        selector.addAttribute("name");
        List<String> contents = selector.select(document);
        assertEquals(6, contents.size());
        assertEquals(true, contents.contains("tj_trnews"));
        assertEquals(true, contents.contains("tj_trhao123"));
        assertEquals(true, contents.contains("tj_trmap"));
        assertEquals(true, contents.contains("tj_trvideo"));
        assertEquals(true, contents.contains("tj_trtieba"));
        assertEquals(true, contents.contains("tj_trxueshu"));
    }

    @Test
    public void testSelect2() {
        HtmlAttrSelector selector = new HtmlAttrSelector("urlPattern", "a.mnav");
        List<String> contents = selector.select(document);
        assertEquals(6, contents.size());
        assertEquals(true, contents.contains("新闻"));
        assertEquals(true, contents.contains("hao123"));
        assertEquals(true, contents.contains("地图"));
        assertEquals(true, contents.contains("视频"));
        assertEquals(true, contents.contains("贴吧"));
        assertEquals(true, contents.contains("学术"));
    }
}