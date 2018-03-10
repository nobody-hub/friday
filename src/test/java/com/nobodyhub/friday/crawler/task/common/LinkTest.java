package com.nobodyhub.friday.crawler.task.common;

import com.google.common.collect.Maps;
import com.nobodyhub.friday.crawler.task.SerializationTest;
import com.nobodyhub.friday.crawler.task.html.HtmlLink;
import com.nobodyhub.friday.crawler.task.html.selector.HtmlUrlSelector;
import com.nobodyhub.friday.crawler.task.json.JsonLink;
import com.nobodyhub.friday.crawler.task.json.selector.JsonAttrSelector;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.jsoup.Connection.Method.GET;
import static org.junit.Assert.assertEquals;

/**
 * @author Ryan
 */
public class LinkTest implements SerializationTest {
    private Request request;

    @Before
    public void setup() {
        request = new Request(GET, Maps.newHashMap(), "nobody");
    }

    @Test
    public void testHtmlLink() throws IOException {
        String json = "{\"urlPattern\":\"urlPattern\",\"selector\":{\"urlPattern\":\"pattern\",\"selector\":\"selectpr\",\"type\":\"URL\"},\"request\":{\"method\":\"GET\",\"headers\":{},\"requestBody\":\"nobody\"}}";
        HtmlLink link = new HtmlLink("urlPattern", new HtmlUrlSelector("pattern", "selectpr"), request);
        assertEquals(json, objectMapper.writeValueAsString(link));
        assertEquals(link, objectMapper.readValue(json, HtmlLink.class));
    }

    @Test
    public void testJsonLink() throws IOException {
        String json = "{\"urlPattern\":\"urlPattern\",\"selector\":{\"urlPattern\":\"pattern\",\"selector\":\"selectpr\",\"type\":\"TEXT\"},\"request\":{\"method\":\"GET\",\"headers\":{},\"requestBody\":\"nobody\"}}";
        JsonLink link = new JsonLink("urlPattern", new JsonAttrSelector("pattern", "selectpr"), request);
        assertEquals(json, objectMapper.writeValueAsString(link));
        assertEquals(link, objectMapper.readValue(json, JsonLink.class));
    }

}