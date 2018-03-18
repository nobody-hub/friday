package com.nobodyhub.friday.crawler.task.html.selector;

import com.nobodyhub.friday.crawler.task.html.HtmlSelectorPatternTest;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @author Ryan
 */
public class HtmlImageSelectorPatternTest extends HtmlSelectorPatternTest {
    @Test
    public void testSelect() {
        this.selector = new HtmlImageSelectorPattern("urlPattern", "img[hidefocus=true]");
        List<String> contents = this.selector.select(document);
        assertEquals(1, contents.size());
        assertEquals(true, contents.contains("http://www.baidu.com/img/bd_logo1.png"));
    }
}