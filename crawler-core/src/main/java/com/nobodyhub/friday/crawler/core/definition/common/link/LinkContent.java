package com.nobodyhub.friday.crawler.core.definition.common.link;

import lombok.RequiredArgsConstructor;
import org.jsoup.Connection;

import java.io.IOException;

/**
 * Content downloaded from {@link this#link}
 *
 * @author Ryan
 */
@RequiredArgsConstructor
public abstract class LinkContent<DOC> {
    protected final Link link;
    protected final DOC document;
    protected final Connection.Response response;

    /**
     * extrac the actual contents from {@link this#response}
     *
     * @return
     * @throws IOException
     */
    public abstract DOC getDocument() throws IOException;
}
