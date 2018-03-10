package com.nobodyhub.friday.crawler.task.common;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.jsoup.Connection;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.util.Map;

/**
 * Request used to connect to given link
 *
 * @author Ryan
 */
@Data
@EqualsAndHashCode
@ToString
public class Request {
    protected Connection.Method method;
    protected Map<String, String> headers;
    protected String requestBody;

    public Connection.Response execute(String url) throws IOException {
        return Jsoup.connect(url)
                .method(method)
                .headers(headers)
                .requestBody(requestBody)
                .execute();
    }
}
