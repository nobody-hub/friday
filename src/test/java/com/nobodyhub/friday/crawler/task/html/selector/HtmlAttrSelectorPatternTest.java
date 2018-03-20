package com.nobodyhub.friday.crawler.task.html.selector;

import com.google.common.collect.Lists;
import com.nobodyhub.friday.crawler.task.common.SelectorResult;
import com.nobodyhub.friday.crawler.task.html.HtmlSelectorPatternTest;
import org.junit.Test;

import java.util.List;

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
        //TODO
//        assertEquals(true, contents.contains("tj_trnews"));
//        assertEquals(true, contents.contains("tj_trhao123"));
//        assertEquals(true, contents.contains("tj_trmap"));
//        assertEquals(true, contents.contains("tj_trvideo"));
//        assertEquals(true, contents.contains("tj_trtieba"));
//        assertEquals(true, contents.contains("tj_trxueshu"));
    }

    @Test
    public void testSelectWithoutAttr() {
        selector = new HtmlAttrSelectorPattern("urlPattern", Lists.newArrayList("a.mnav"));
        List<SelectorResult> contents = selector.select("http://www.baidu.com", document);
        assertEquals(6, contents.size());
        //TODO
//        assertEquals(true, contents.contains("新闻"));
//        assertEquals(true, contents.contains("hao123"));
//        assertEquals(true, contents.contains("地图"));
//        assertEquals(true, contents.contains("视频"));
//        assertEquals(true, contents.contains("贴吧"));
//        assertEquals(true, contents.contains("学术"));
    }
}