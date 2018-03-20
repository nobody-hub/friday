package com.nobodyhub.friday.crawler.task.json;

import com.google.common.base.Charsets;
import com.google.common.collect.Lists;
import com.google.common.io.Resources;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.ReadContext;
import com.nobodyhub.friday.crawler.task.common.SelectorResult;
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
        JsonAttrSelectorPattern selector = new JsonAttrSelectorPattern("urlPattern", Lists.newArrayList("paging.next", "data[0].actor.url"));
        List<SelectorResult> contents = selector.select("http://www.zhihu.com", document);
        assertEquals(2, contents.size());
        List<String> values = convertToAttrList(contents);
        assertEquals(true, values.contains("https://www.zhihu.com/api/v4/members/zhang-jia-wei-64/activities?limit=8&after_id=1461208563&desktop=True"));
        assertEquals(true, values.contains("https://api.zhihu.com/people/70e8e4eb769ecac38a805246ff1d268c"));

    }

    protected List<String> convertToAttrList(List<SelectorResult> results) {
        List<String> values = Lists.newArrayList();
        for (SelectorResult result : results) {
            values.addAll(result.getAttributeMap().values());
        }
        return values;
    }
}