package com.nobodyhub.friday.crawler.core.dowload;

import com.nobodyhub.friday.crawler.core.definition.common.link.LinkContent;

/**
 * @author Ryan
 */
public interface Callback {
    /**
     * called when execution starts
     */
    void onStart();

    /**
     * called when execution success
     *
     * @param content
     */
    void onSuccess(LinkContent content);

    /**
     * called when execution fail
     *
     * @param e
     */
    void onFail(Exception e);

    /**
     * call when execution finish, no matter success or fail
     */
    void onFinish();
}
