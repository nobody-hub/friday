package com.nobodyhub.friday.crawler.request;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * Request used to connect to given link
 *
 * @author Ryan
 */
@Data
@EqualsAndHashCode
@ToString
public class Request {
    protected String method;
    protected String version;
    protected RequestHeader header;
    protected String requestBody;
}
