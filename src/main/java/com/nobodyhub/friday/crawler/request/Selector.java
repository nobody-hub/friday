package com.nobodyhub.friday.crawler.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.Lists;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.List;

/**
 * Selector to locate element on page
 *
 * @author Ryan
 */
@EqualsAndHashCode
@ToString
public class Selector {
    /**
     * type of selector
     */
    @JsonProperty
    protected SelectorType type;
    /**
     * the selector text
     */
    @JsonProperty
    protected String sel;
    /**
     * attributes in which crawler is interested
     * if attribute for selector is empty, the whole element that is selected are the target
     */
    @JsonProperty
    protected List<Attribute> attributes = Lists.newArrayList();

    public void setCssSelector(String selText) {
        this.type = SelectorType.CSS;
        this.sel = selText;
    }

    public void addAttribute(String attr) {
        Attribute attribute = new Attribute();
        attribute.setAttr(attr);
        this.attributes.add(attribute);
    }

    public List<String> getAttrValues(Document document) {
        List<String> values = Lists.newArrayList();
        Elements elements = document.select(sel);
        for (Element element : elements) {
            for (Attribute attr : attributes) {
                values.add(element.attr(attr.getAttr()));
            }
        }
        return values;
    }
}

/**
 * Type of Selector
 */
enum SelectorType {
    CSS,
    XPATH,
    JSON
}
