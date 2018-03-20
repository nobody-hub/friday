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
public class HtmlVideoSelectorPatternTest extends HtmlSelectorPatternTest {
    @Test
    public void testSelect() {
        this.selector = new HtmlVideoSelectorPattern("urlPattern", Lists.newArrayList("video.selected"));
        List<SelectorResult> contents = this.selector.select("http://nobodyhub.com/", document);
        assertEquals(2, contents.size());
        //TODO
//        assertEquals(true, contents.contains("http://nobodyhub.com/selector/movie.mp4"));
//        assertEquals(true, contents.contains("http://nobodyhub.com/selector/movie.ogg"));
    }
}