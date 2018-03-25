package com.nobodyhub.friday.crawler.core.definition.html;

import com.nobodyhub.friday.crawler.core.definition.common.link.Link;
import com.nobodyhub.friday.crawler.core.definition.common.link.LinkContent;
import org.jsoup.Connection;
import org.jsoup.nodes.Document;

import java.io.IOException;

/**
 * @author Ryan
 */
public class HtmlLinkContent extends LinkContent<Document> {
    public HtmlLinkContent(Link link, Document document, Connection.Response response) {
        super(link, document, response);
    }

    @Override
    public Document getDocument() throws IOException {
        return response.parse();
    }
}
