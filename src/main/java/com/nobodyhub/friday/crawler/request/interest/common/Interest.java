package com.nobodyhub.friday.crawler.request.interest.common;

import com.google.common.collect.Lists;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.regex.Pattern;

/**
 * @author Ryan
 */
@Getter
@RequiredArgsConstructor
public abstract class Interest<CONTENTTYPE, DOCUMENT, SELECTOR extends Selector<DOCUMENT, CONTENTTYPE>> {
    protected final InterestType type;

    protected final List<SELECTOR> selectors = Lists.newArrayList();

    protected final String urlPattern;

    public abstract List<CONTENTTYPE> fetch(DOCUMENT document);

    public void addSelector(SELECTOR selector) {
        this.selectors.add(selector);
    }

    public boolean matches(String url) {
        if (url == null) {
            return false;
        }
        return Pattern.compile(urlPattern).matcher(url).find();
    }

}
