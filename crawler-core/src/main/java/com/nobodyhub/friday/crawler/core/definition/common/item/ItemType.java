package com.nobodyhub.friday.crawler.core.definition.common.item;

/**
 * Types of Interested Contents
 *
 * @author Ryan
 */
public enum ItemType {
    /**
     * Absolute URL
     * the difference with {@link this#TEXT} is the value of {@link this#URL} might be a base URL + value of {@link this#TEXT}
     */
    URL,
    /**
     * Text, usually the attribute of element
     */
    TEXT,
    /**
     * Imaage
     */
    IMAGE,
    /**
     * Audio
     */
    AUDIO,
    /**
     * Video
     */
    VIDEO
}
