package com.nobodyhub.friday.crawler.task.html.selector;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @author Ryan
 */
public class HtmlAudioSelectorTest extends HtmlSelectorTest {
    @Test
    public void testSelect() {
        this.selector = new HtmlAudioSelector("urlPattern", "audio.selected");
        List<String> contents = this.selector.select(document);
        assertEquals(2, contents.size());
        assertEquals(true, contents.contains("http://nobodyhub.com/selector/horse.mp3"));
        assertEquals(true, contents.contains("http://nobodyhub.com/selector/horse.ogg"));
    }
}