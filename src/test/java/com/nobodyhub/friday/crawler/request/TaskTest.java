package com.nobodyhub.friday.crawler.request;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author Ryan
 */
public class TaskTest {
    private Task task;
    private String taskJson;
    private ObjectMapper objectMapper;

    @Before
    public void setup() {
        this.task = createTask();
        taskJson = "{\"name\":\"TaskName\",\"description\":\"TaskDescription\",\"version\":\"1.2.3\",\"userAgent\":\"Friday\",\"entranceUrls\":[\"http://en.wikipedia.org/\"],\"links\":[{\"urlPattern\":\"en\\\\.wikipedia\\\\.org/wiki/.*\",\"request\":null}],\"targets\":[{\"urlPattern\":\"en\\\\.wikipedia\\\\.org/wiki/.*\",\"selectors\":[{\"type\":\"CSS\",\"sel\":\"b a\",\"attributes\":[{\"attr\":\"href\"}]}]}]}";
        objectMapper = new ObjectMapper();
    }

    @Test
    public void toJson() throws JsonProcessingException {
        assertEquals(taskJson, objectMapper.writeValueAsString(task));
    }

    @Test
    public void fromJson() throws IOException {
        assertEquals(this.task, objectMapper.readValue(this.taskJson, Task.class));
    }

    private Task createTask() {
        Task task = new Task();
        task.setName("TaskName");
        task.setDescription("TaskDescription");
        task.setVersion("1.2.3");
        task.setUserAgent("Friday");
        task.setEntranceUrls(Lists.newArrayList(
                "http://en.wikipedia.org/"
        ));

        Link link = new Link();
        link.setUrlPattern("en\\.wikipedia\\.org/wiki/.*");
        task.setLinks(Lists.newArrayList(link));

        Target target = new Target();
        target.setUrlPattern("en\\.wikipedia\\.org/wiki/.*");
        Selector selector = new Selector();
        selector.setCssSelector("b a");
        selector.addAttribute("href");
        target.setSelectors(Lists.newArrayList(
                selector
        ));
        task.setTargets(Lists.newArrayList(target));
        return task;
    }

    @Test
    public void parseLink() throws IOException {
        Document document = Jsoup.connect(task.getEntranceUrls().get(0)).get();
        List<String> links = task.parseLink(document);
        assertTrue(links.size() > 0);
    }

    @Test
    public void parseTarget() throws IOException {
        Document document = Jsoup.connect(task.getEntranceUrls().get(0)).get();
        List<Object> targets = task.parseTarget(document);
        assertTrue(targets.size() > 0);
    }
}