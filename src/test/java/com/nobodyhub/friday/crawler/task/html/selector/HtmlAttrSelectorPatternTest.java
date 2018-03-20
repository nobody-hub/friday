package com.nobodyhub.friday.crawler.task.html.selector;

import com.google.common.collect.Lists;
import com.nobodyhub.friday.crawler.task.common.ContentType;
import com.nobodyhub.friday.crawler.task.common.SelectorResult;
import com.nobodyhub.friday.crawler.task.html.HtmlSelectorPatternTest;
import org.junit.Test;

import java.util.List;

import static com.nobodyhub.friday.crawler.task.common.SelectorResult.HTML_TEXT;
import static org.junit.Assert.assertEquals;

/**
 * @author Ryan
 */
public class HtmlAttrSelectorPatternTest extends HtmlSelectorPatternTest {

    @Test
    public void testSelectWithAttr() {
        selector = new HtmlAttrSelectorPattern("urlPattern", Lists.newArrayList("a.mnav"));
        ((HtmlAttrSelectorPattern) selector).addAttribute("name");
        List<SelectorResult> contents = selector.select("http://www.baidu.com", document);
        assertEquals(6, contents.size());
        List<String> values = convertToAttrList(contents, "name");
        assertEquals("a.mnav", contents.get(0).getSelector());
        assertEquals("http://www.baidu.com", contents.get(0).getUrl());
        assertEquals(ContentType.TEXT, contents.get(0).getType());
        assertEquals(true, values.contains("tj_trnews"));
        assertEquals(true, values.contains("tj_trhao123"));
        assertEquals(true, values.contains("tj_trmap"));
        assertEquals(true, values.contains("tj_trvideo"));
        assertEquals(true, values.contains("tj_trtieba"));
        assertEquals(true, values.contains("tj_trxueshu"));
    }

    @Test
    public void testSelectWithoutAttr() {
        selector = new HtmlAttrSelectorPattern("urlPattern", Lists.newArrayList("a.mnav"));
        List<SelectorResult> contents = selector.select("http://www.baidu.com", document);
        assertEquals(6, contents.size());
        List<String> values = convertToAttrList(contents, HTML_TEXT);
        assertEquals("a.mnav", contents.get(0).getSelector());
        assertEquals("http://www.baidu.com", contents.get(0).getUrl());
        assertEquals(ContentType.TEXT, contents.get(0).getType());
        assertEquals(true, values.contains("新闻"));
        assertEquals(true, values.contains("hao123"));
        assertEquals(true, values.contains("地图"));
        assertEquals(true, values.contains("视频"));
        assertEquals(true, values.contains("贴吧"));
        assertEquals(true, values.contains("学术"));
    }
}