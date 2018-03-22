package com.nobodyhub.friday.crawler.core.definition.common.link;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.Maps;
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
    protected final Map<String, String> cookies;
    protected final String requestBody;

    @JsonCreator
    public Request(
            @JsonProperty("method") Connection.Method method,
            @JsonProperty("headers") Map<String, String> headers,
            @JsonProperty("cookies") Map<String, String> cookies,
            @JsonProperty("requestBody") String requestBody) {
        this.method = method;
        this.headers = headers;
        this.cookies = cookies;
        this.requestBody = requestBody;
    }

    /**
     * a deep copy
     *
     * @param request
     */
    public Request(Request request) {
        this.method = request.getMethod();
        this.headers = Maps.newHashMap(request.getHeaders());
        this.cookies = Maps.newHashMap(request.getCookies());
        this.requestBody = request.requestBody;
    }

    public Connection.Response execute(String url) throws IOException {
        return Jsoup.connect(url)
                .method(method)
                .headers(headers)
                .requestBody(requestBody)
                .execute();
    }


    /**
     * Add new key-value to Headers
     * <p>
     * If there is more than one header value with the same name, the headers are returned
     * comma seperated, per <a href="https://www.w3.org/Protocols/rfc2616/rfc2616-sec4.html#sec4.2">rfc2616-sec4</a>.s
     *
     * @param fieldName
     * @param fieldValue
     */
    public void addHeader(String fieldName, String fieldValue) {
        if (headers.containsKey(fieldName)) {
            headers.put(fieldName, fieldValue + headers.get(fieldName));
        } else {
            headers.put(fieldName, fieldValue);
        }
    }

    /**
     * Add value of {@code newHeaders} to {@link this#headers}
     *
     * @param newHeaders
     */
    protected void addHeader(Map<String, String> newHeaders) {
        for (Map.Entry<String, String> entry : newHeaders.entrySet()) {
            String fieldName = entry.getKey();
            String fieldValue = entry.getValue();
            addHeader(fieldName, fieldValue);
        }
    }

    /**
     * Updade cookies values
     *
     * @param key
     * @param value
     */
    public void updateCookies(String key, String value) {
        cookies.put(key, value);
    }

    /**
     * Update {@link this#cookies} with the {@code newCookies}
     *
     * @param newCookies
     */
    protected void updateCookies(Map<String, String> newCookies) {
        for (Map.Entry<String, String> entry : newCookies.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            updateCookies(key, value);
        }
    }

    /**
     * @param response
     * @return a new updated Request instance using the {@code response} from last request
     */
    public Request update(Connection.Response response) {
        Request request = new Request(this);
        request.addHeader(response.headers());
        request.updateCookies(response.cookies());
        return request;
    }
}
