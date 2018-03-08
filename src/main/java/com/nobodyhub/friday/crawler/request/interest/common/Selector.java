package com.nobodyhub.friday.crawler.request.interest.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

/**
 * @author Ryan
 */
@RequiredArgsConstructor
@Getter
public abstract class Selector<DOCUMENT, CONTENTTYPE> {
    /**
     * the selector text
     */
    protected final String selector;

    public abstract List<CONTENTTYPE> select(DOCUMENT document);
}


