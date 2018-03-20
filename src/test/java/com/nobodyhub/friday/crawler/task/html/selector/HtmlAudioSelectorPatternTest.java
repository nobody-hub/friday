package com.nobodyhub.friday.crawler.task.html.selector;

import com.google.common.collect.Lists;
import com.nobodyhub.friday.crawler.task.common.SelectorResult;
import com.nobodyhub.friday.crawler.task.html.HtmlSelectorPatternTest;
import org.junit.Test;

import java.util.List;

import static com.nobodyhub.friday.crawler.task.common.SelectorResult.HTML_SRC;
import static org.junit.Assert.assertEquals;

/**
 * @author Ryan
 */
public class HtmlAudioSelectorPatternTest extends HtmlSelectorPatternTest {
    @Test
    public void testSelect() {
        this.selector = new HtmlAudioSelectorPattern("urlPattern", Lists.newArrayList("audio.selected"));
        List<SelectorResult> contents = this.selector.select("http://nobodyhub.com/", document);
        assertEquals(2, contents.size());
        List<String> values = convertToAttrList(contents, HTML_SRC);
        assertEquals(true, values.contains("http://nobodyhub.com/selector/horse.mp3"));
        assertEquals(true, values.contains("http://nobodyhub.com/selector/horse.ogg"));
    }
}