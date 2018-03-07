package com.nobodyhub.friday.crawler.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.Lists;
import lombok.Data;
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
@Data
public class Selector {
    /**
     * type of selector
     */
    @JsonProperty
    protected SelectorType type;
    /**
     * the target type of selector
     */
    @JsonProperty
    protected SelectTargetType targetType;
    /**
     * the selector text
     */
    @JsonProperty
    protected String sel;
    /**
     * attributes, namely {@link SelectTargetType#TEXT}, in which crawler is interested
     * if attribute for selector is empty, the whole element that is selected are the target
     */
    @JsonProperty
    protected List<Attribute> attributes = Lists.newArrayList();

    /**
     * Request to fetch
     * {@link SelectTargetType#JSON}
     * {@link SelectTargetType#IMAGE}ta
     * {@link SelectTargetType#AUDIO}
     * {@link SelectTargetType#VIDEO}
     */
    @JsonProperty
    protected Request request;

    public void setCssSelector(String selText) {
        this.type = SelectorType.CSS;
        this.sel = selText;
    }

    public void addAttribute(String attr) {
        this.targetType = SelectTargetType.TEXT;
        Attribute attribute = new Attribute();
        attribute.setAttr(attr);
        this.attributes.add(attribute);
    }

    public List<String> getAttrText(Document document) {
        List<String> values = Lists.newArrayList();
        if (SelectTargetType.TEXT.equals(this.targetType)) {
            Elements elements = document.select(sel);
            for (Element element : elements) {
                for (Attribute attr : attributes) {
                    values.add(element.attr(attr.getAttr()));
                }
            }
        }
        return values;
    }

    public Object getContent(Document document) {
        //TODO: fetch contents based on the {@link Selector@request}
        return null;
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

/**
 * type of select target
 */
enum SelectTargetType {
    TEXT,
    JSON,
    IMAGE,
    VIDEO,
    AUDIO
}
