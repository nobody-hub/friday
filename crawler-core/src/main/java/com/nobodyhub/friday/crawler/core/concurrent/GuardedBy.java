package com.nobodyhub.friday.crawler.core.concurrent;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Indicate which lock which be used to guarantee the status
 *
 * @author Ryan
 */
@Retention(RetentionPolicy.CLASS)
@Target(ElementType.FIELD)
public @interface GuardedBy {
    String value();
}
