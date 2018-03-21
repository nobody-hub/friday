package com.nobodyhub.friday.crawler.definition.html.selector;

import com.google.common.collect.Lists;
import com.nobodyhub.friday.crawler.definition.common.item.Item;
import com.nobodyhub.friday.crawler.definition.html.HtmlItemPatternTest;
import org.junit.Test;

import java.util.List;

import static com.nobodyhub.friday.crawler.definition.common.item.Item.HTML_SRC;
import static org.junit.Assert.assertEquals;

/**
 * @author Ryan
 */
public class HtmlImageItemPatternTest extends HtmlItemPatternTest {
    @Test
    public void testSelect() {
        this.selector = new HtmlImageItemPattern("urlPattern", Lists.newArrayList("img[hidefocus=true]"));
        List<Item> contents = this.selector.select("http://www.baidu.com/", document);
        assertEquals(1, contents.size());
        List<String> values = convertToAttrList(contents, HTML_SRC);
        assertEquals(true, values.contains("http://www.baidu.com/img/bd_logo1.png"));
    }
}