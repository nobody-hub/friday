package com.nobodyhub.friday.crawler.task.json;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.ReadContext;
import com.nobodyhub.friday.crawler.task.json.selector.JsonAttrSelectorPattern;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @author Ryan
 */
public class JsonSelectorPatternTest {
    protected ReadContext document;

    @Before
    public void setup() throws IOException {
        document = JsonPath.parse(Resources.toString(getClass().getClassLoader().getResource("com/nobodyhub/friday/crawler/task/json/selector.json"), Charsets.UTF_8));
    }

    @Test
    public void testSelect() {
        JsonAttrSelectorPattern selector = new JsonAttrSelectorPattern("urlPattern", "paging.next");
        List<String> contents = selector.select(document);
        assertEquals(1, contents.size());
        assertEquals(true, contents.contains("https://www.zhihu.com/api/v4/members/zhang-jia-wei-64/activities?limit=8&after_id=1461208563&desktop=True"));
    }
}