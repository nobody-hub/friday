package com.nobodyhub.friday.crawler.core.concurrent;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Indicate the class is not thread safe
 *
 * @author Ryan
 */
@Retention(RetentionPolicy.CLASS)
@Target(ElementType.TYPE)
public @interface NotThreadSafe {
}
