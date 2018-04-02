package com.nobodyhub.friday.crawler.core.execution;

import com.nobodyhub.friday.crawler.core.definition.common.item.Item;
import com.nobodyhub.friday.crawler.core.definition.common.link.Link;
import com.nobodyhub.friday.crawler.core.definition.common.link.LinkContent;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

/**
 * @author Ryan
 */
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public final class TaskExecutionMetadata {
    /**
     * the unique identifire of task,
     * used as Group Id for Kafka consumer
     */
    private final String taskId;
    /**
     * Kafka Topic for {@link Link}
     */
    private final String linkTopic;
    /**
     * Kafka Topic for {@link LinkContent}
     */
    private final String contentTopic;
    /**
     * Kafka Topic for {@link Item}
     */
    private final String itemTopic;

    public static TaskExecutionMetadata generate() {
        String uuid = UUID.randomUUID().toString();
        return new TaskExecutionMetadata(
                "task-" + uuid,
                "link-" + uuid,
                "content-" + uuid,
                "item-" + uuid);
    }
}
