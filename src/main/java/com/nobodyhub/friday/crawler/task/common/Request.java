package com.nobodyhub.friday.crawler.task.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
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
@ToString
public class Request {
    protected final Connection.Method method;
    protected final Map<String, String> headers;
    protected final String requestBody;

    public Request(
            @JsonProperty("method") Connection.Method method,
            @JsonProperty("headers") Map<String, String> headers,
            @JsonProperty("requestBody") String requestBody) {
        this.method = method;
        this.headers = headers;
        this.requestBody = requestBody;
    }

    public Connection.Response execute(String url) throws IOException {
        return Jsoup.connect(url)
                .method(method)
                .headers(headers)
                .requestBody(requestBody)
                .execute();
    }
}
