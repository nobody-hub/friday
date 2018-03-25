package com.nobodyhub.friday.crawler.core.definition.common.link;

import lombok.Getter;
import org.jsoup.Connection;

import java.io.IOException;

/**
 * Content downloaded from {@link this#link}
 *
 * @author Ryan
 */
@Getter
public abstract class LinkContent<DOC> {
    protected final Link link;
    protected final DOC document;

    public LinkContent(Link link, Connection.Response response) throws IOException {
        this.link = new Link(link.getUrl(), link.getRequest().copyAndUpdate(response));
        this.document = parseContent(response);
    }

    /**
     * Parse target contents from Reponse
     *
     * @param response
     * @return
     * @throws IOException
     */
    protected abstract DOC parseContent(Connection.Response response) throws IOException;
}
