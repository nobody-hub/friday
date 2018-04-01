package com.nobodyhub.friday.crawler.core.definition.json;

import com.google.common.base.Charsets;
import com.google.common.collect.Lists;
import com.google.common.io.Resources;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.ReadContext;
import com.nobodyhub.friday.crawler.core.definition.common.item.Item;
import com.nobodyhub.friday.crawler.core.definition.json.selector.JsonAttrItemPattern;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @author Ryan
 */
public class JsonItemPatternTest {
    protected ReadContext document;
    @Mock
    protected JsonLinkContent content;

    @Before
    public void setup() throws IOException {
        MockitoAnnotations.initMocks(this);
        document = JsonPath.parse(Resources.toString(getClass().getClassLoader().getResource("com/nobodyhub/friday/crawler/core/definition/json/selector.json"), Charsets.UTF_8));
        Mockito.when(content.getDocument()).thenReturn(document);
    }

    @Test
    public void testSelect() {
        JsonAttrItemPattern selector = new JsonAttrItemPattern("urlPattern", Lists.newArrayList("paging.next", "data[0].actor.url"));
        List<Item> contents = selector.select("http://www.zhihu.com", content);
        assertEquals(2, contents.size());
        List<String> values = convertToAttrList(contents);
        assertEquals(true, values.contains("https://www.zhihu.com/api/v4/members/zhang-jia-wei-64/activities?limit=8&after_id=1461208563&desktop=True"));
        assertEquals(true, values.contains("https://api.zhihu.com/people/70e8e4eb769ecac38a805246ff1d268c"));

    }

    protected List<String> convertToAttrList(List<Item> results) {
        List<String> values = Lists.newArrayList();
        for (Item result : results) {
            values.addAll(result.getAttributeMap().values());
        }
        return values;
    }
}