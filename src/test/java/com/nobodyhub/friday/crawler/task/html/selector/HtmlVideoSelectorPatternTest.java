package com.nobodyhub.friday.crawler.task.html.selector;

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
        this.selector = new HtmlVideoSelectorPattern("urlPattern", "video.selected");
        List<String> contents = this.selector.select(document);
        assertEquals(2, contents.size());
        assertEquals(true, contents.contains("http://nobodyhub.com/selector/movie.mp4"));
        assertEquals(true, contents.contains("http://nobodyhub.com/selector/movie.ogg"));
    }
}