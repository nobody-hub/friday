package com.nobodyhub.friday.crawler.task.common;

import com.google.common.collect.Maps;
import com.nobodyhub.friday.crawler.task.SerializationTest;
import com.nobodyhub.friday.crawler.task.html.HtmlLink;
import com.nobodyhub.friday.crawler.task.html.HtmlTask;
import com.nobodyhub.friday.crawler.task.html.selector.HtmlUrlSelector;
import com.nobodyhub.friday.crawler.task.json.JsonLink;
import com.nobodyhub.friday.crawler.task.json.JsonTask;
import com.nobodyhub.friday.crawler.task.json.selector.JsonAttrSelector;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.jsoup.Connection.Method.GET;
import static org.junit.Assert.assertEquals;

/**
 * @author Ryan
 */
public class TaskTest implements SerializationTest {
    private Request request;

    @Before
    public void setup() {
        request = new Request(GET, Maps.newHashMap(), "nobody");
    }

    @Test
    public void testJsonTask() throws IOException {
        String json = "{\"name\":\"name\",\"description\":\"description\",\"version\":\"version\",\"userAgent\":\"userAgent\",\"entranceUrls\":[\"entranceUrl\"],\"taskType\":\"JSON\",\"links\":[{\"urlPattern\":\"urlPattern\",\"selector\":{\"urlPattern\":\"pattern\",\"selector\":\"selectpr\",\"type\":\"TEXT\"},\"request\":{\"method\":\"GET\",\"headers\":{},\"requestBody\":\"nobody\"}}],\"selectors\":[{\"urlPattern\":\"urlPattern\",\"selector\":\"selector\",\"type\":\"TEXT\"}]}";
        JsonTask task = new JsonTask("name", "description", "version", "userAgent");
        task.addEntranceUrl("entranceUrl");
        task.addSelector(new JsonAttrSelector("urlPattern", "selector"));
        task.addLink(new JsonLink("urlPattern", new JsonAttrSelector("pattern", "selectpr"), request));
        assertEquals(json, objectMapper.writeValueAsString(task));
        assertEquals(task, objectMapper.readValue(json, Task.class));
    }

    @Test
    public void testHtmlTask() throws IOException {
        String json = "{\"name\":\"name\",\"description\":\"description\",\"version\":\"version\",\"userAgent\":\"userAgent\",\"entranceUrls\":[\"entranceUrl\"],\"taskType\":\"HTML\",\"links\":[{\"urlPattern\":\"urlPattern\",\"selector\":{\"urlPattern\":\"pattern\",\"selector\":\"selectpr\",\"type\":\"URL\"},\"request\":{\"method\":\"GET\",\"headers\":{},\"requestBody\":\"nobody\"}}],\"selectors\":[{\"urlPattern\":\"urlPattern\",\"selector\":\"selector\",\"type\":\"URL\"}]}";
        HtmlTask task = new HtmlTask("name", "description", "version", "userAgent");
        task.addEntranceUrl("entranceUrl");
        task.addSelector(new HtmlUrlSelector("urlPattern", "selector"));
        task.addLink(new HtmlLink("urlPattern", new HtmlUrlSelector("pattern", "selectpr"), request));
        assertEquals(json, objectMapper.writeValueAsString(task));
        assertEquals(task, objectMapper.readValue(json, Task.class));
    }

}