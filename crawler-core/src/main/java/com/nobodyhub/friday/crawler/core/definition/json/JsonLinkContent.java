package com.nobodyhub.friday.crawler.core.definition.json;

import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.ReadContext;
import com.nobodyhub.friday.crawler.core.definition.common.link.Link;
import com.nobodyhub.friday.crawler.core.definition.common.link.LinkContent;
import org.jsoup.Connection;

import java.io.IOException;

/**
 * @author Ryan
 */
public class JsonLinkContent extends LinkContent<ReadContext> {
    public JsonLinkContent(Link link, Connection.Response response) throws IOException {
        super(link, response);
    }

    @Override
    protected ReadContext parseContent(Connection.Response response) throws IOException {
        return JsonPath.parse(response.body());
    }

}
