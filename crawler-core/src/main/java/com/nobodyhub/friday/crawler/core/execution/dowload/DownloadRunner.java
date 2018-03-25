package com.nobodyhub.friday.crawler.core.execution.dowload;

import com.nobodyhub.friday.crawler.core.definition.common.link.Link;
import com.nobodyhub.friday.crawler.core.definition.common.task.Task;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;

/**
 * @author Ryan
 */
@RequiredArgsConstructor
public abstract class DownloadRunner implements Runnable, Callback {

    protected final ConsumerRecord<String, Link> record;

    protected final Task task;

    @Override
    public void run() {
        try {
            onStart();
            Link link = record.value();
            onSuccess(task.connect(link));
        } catch (Exception e) {
            onFail(e);
        } finally {
            onFinish();
        }
    }
}
