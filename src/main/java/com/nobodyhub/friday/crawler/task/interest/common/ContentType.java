package com.nobodyhub.friday.crawler.task.interest.common;

/**
 * Types of Interested Contents
 *
 * @author Ryan
 */
public enum ContentType {
    /**
     * text of attributes of HTML
     */
    TEXT,
    /**
     * Imaage included in HTML
     */
    IMAGE,
    /**
     * Audio included in HTML
     */
    AUDIO,
    /**
     * Video included in HTML
     */
    VIDEO,
    /**
     * JSON properties included in JSON object
     */
    JSON
}
