package com.nobodyhub.friday.crawler.task.common;

import com.nobodyhub.friday.crawler.task.SerializationTest;
import com.nobodyhub.friday.crawler.task.html.HtmlSelector;
import com.nobodyhub.friday.crawler.task.html.selector.*;
import com.nobodyhub.friday.crawler.task.json.JsonSelector;
import com.nobodyhub.friday.crawler.task.json.selector.JsonAttrSelector;
import com.nobodyhub.friday.crawler.task.json.selector.JsonAudioSelector;
import com.nobodyhub.friday.crawler.task.json.selector.JsonImageSelector;
import com.nobodyhub.friday.crawler.task.json.selector.JsonVideoSelector;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

/**
 * @author Ryan
 */
public class SelectorTest implements SerializationTest {

    @Test
    public void testHtmlAttrSelector() throws IOException {
        String json = "{\"urlPattern\":\"urlPattern\",\"selector\":\"selector\",\"type\":\"TEXT\",\"attributes\":[\"attribute1\"]}";
        HtmlAttrSelector selector = new HtmlAttrSelector("urlPattern", "selector");
        selector.addAttribute("attribute1");

        assertEquals(json, objectMapper.writeValueAsString(selector));
        assertEquals(selector, objectMapper.readValue(json, HtmlSelector.class));
    }

    @Test
    public void testHtmlImageSelector() throws IOException {
        String json = "{\"urlPattern\":\"urlPattern\",\"selector\":\"selector\",\"type\":\"IMAGE\"}";
        HtmlImageSelector selector = new HtmlImageSelector("urlPattern", "selector");

        assertEquals(json, objectMapper.writeValueAsString(selector));
        assertEquals(selector, objectMapper.readValue(json, HtmlSelector.class));
    }

    @Test
    public void testHtmlAudioSelector() throws IOException {
        String json = "{\"urlPattern\":\"urlPattern\",\"selector\":\"selector\",\"type\":\"AUDIO\"}";
        HtmlAudioSelector selector = new HtmlAudioSelector("urlPattern", "selector");

        assertEquals(json, objectMapper.writeValueAsString(selector));
        assertEquals(selector, objectMapper.readValue(json, HtmlSelector.class));
    }

    @Test
    public void testHtmlVideoSelector() throws IOException {
        String json = "{\"urlPattern\":\"urlPattern\",\"selector\":\"selector\",\"type\":\"VIDEO\"}";
        HtmlVideoSelector selector = new HtmlVideoSelector("urlPattern", "selector");

        assertEquals(json, objectMapper.writeValueAsString(selector));
        assertEquals(selector, objectMapper.readValue(json, HtmlSelector.class));
    }

    @Test
    public void testHtmlUrlSelector() throws IOException {
        String json = "{\"urlPattern\":\"urlPattern\",\"selector\":\"selector\",\"type\":\"URL\"}";
        HtmlUrlSelector selector = new HtmlUrlSelector("urlPattern", "selector");
        assertEquals(json, objectMapper.writeValueAsString(selector));
        assertEquals(selector, objectMapper.readValue(json, HtmlSelector.class));
    }

    @Test
    public void testJsonAttrSelector() throws IOException {
        String json = "{\"urlPattern\":\"urlPattern\",\"selector\":\"selector\",\"type\":\"TEXT\"}";
        JsonAttrSelector selector = new JsonAttrSelector("urlPattern", "selector");

        assertEquals(json, objectMapper.writeValueAsString(selector));
        assertEquals(selector, objectMapper.readValue(json, JsonSelector.class));
    }

    @Test
    public void testJsonImageSelector() throws IOException {
        String json = "{\"urlPattern\":\"urlPattern\",\"selector\":\"selector\",\"type\":\"IMAGE\"}";
        JsonImageSelector selector = new JsonImageSelector("urlPattern", "selector");

        assertEquals(json, objectMapper.writeValueAsString(selector));
        assertEquals(selector, objectMapper.readValue(json, JsonSelector.class));
    }

    @Test
    public void testJsonAudioSelector() throws IOException {
        String json = "{\"urlPattern\":\"urlPattern\",\"selector\":\"selector\",\"type\":\"AUDIO\"}";
        JsonAudioSelector selector = new JsonAudioSelector("urlPattern", "selector");

        assertEquals(json, objectMapper.writeValueAsString(selector));
        assertEquals(selector, objectMapper.readValue(json, JsonSelector.class));
    }

    @Test
    public void testJsonVideoSelector() throws IOException {
        String json = "{\"urlPattern\":\"urlPattern\",\"selector\":\"selector\",\"type\":\"VIDEO\"}";
        JsonVideoSelector selector = new JsonVideoSelector("urlPattern", "selector");

        assertEquals(json, objectMapper.writeValueAsString(selector));
        assertEquals(selector, objectMapper.readValue(json, JsonSelector.class));
    }


}