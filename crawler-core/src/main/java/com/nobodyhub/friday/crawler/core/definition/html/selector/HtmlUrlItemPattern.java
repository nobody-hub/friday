package com.nobodyhub.friday.crawler.core.definition.html.selector;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.Lists;
import com.nobodyhub.friday.crawler.core.definition.common.item.Item;
import com.nobodyhub.friday.crawler.core.definition.common.item.ItemType;
import com.nobodyhub.friday.crawler.core.definition.html.HtmlItemPattern;
import com.nobodyhub.friday.crawler.core.definition.html.HtmlLinkContent;
import lombok.EqualsAndHashCode;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.List;

import static com.nobodyhub.friday.crawler.core.definition.common.item.Item.HTML_HREF;

/**
 * @author Ryan
 */
@EqualsAndHashCode(callSuper = true)
public class HtmlUrlItemPattern extends HtmlItemPattern {

    public HtmlUrlItemPattern(
            @JsonProperty("urlPattern") String urlPattern,
            @JsonProperty("selectors") List<String> selectors) {
        super(ItemType.URL, urlPattern, selectors);
    }

    /**
     * @param url
     * @param content
     * @param selector
     * @return
     * @see org.jsoup.nodes.Node#absUrl(String)
     */
    @Override
    protected List<Item> select(String url, HtmlLinkContent content, String selector) {
        List<Item> results = Lists.newArrayList();
        Elements elements = content.getDocument().select(selector);
        for (Element element : elements) {
            Item result = new Item(this.type, url, selector);
            result.addAttr(HTML_HREF, element.absUrl(HTML_HREF));
            results.add(result);
        }
        return results;
    }
}
