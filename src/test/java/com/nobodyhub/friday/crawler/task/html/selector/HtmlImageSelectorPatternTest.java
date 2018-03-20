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
public class HtmlImageSelectorPatternTest extends HtmlSelectorPatternTest {
    @Test
    public void testSelect() {
        this.selector = new HtmlImageSelectorPattern("urlPattern", Lists.newArrayList("img[hidefocus=true]"));
        List<SelectorResult> contents = this.selector.select("http://www.baidu.com/", document);
        assertEquals(1, contents.size());
        List<String> values = convertToAttrList(contents, HTML_SRC);
        assertEquals(true, values.contains("http://www.baidu.com/img/bd_logo1.png"));
    }
}