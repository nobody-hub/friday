package com.nobodyhub.friday.crawler.core.dowload;

import com.nobodyhub.friday.crawler.core.definition.common.link.Link;
import com.nobodyhub.friday.crawler.core.definition.common.link.LinkContent;
import com.nobodyhub.friday.crawler.core.definition.common.task.Task;
import com.nobodyhub.friday.crawler.core.middleware.kafka.ContentKafkaClient;
import com.nobodyhub.friday.crawler.core.middleware.kafka.LinkKafkaClient;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Responsible for tracing and downloading task according to {@link this#task} definition
 *
 * @author Ryan
 */
@RequiredArgsConstructor
public class DownloadExecutor implements Runnable {

    private static final int MAX_THREAD = 5;
    private static final int MAX_QUEUE_SIZE = 20;

    protected final Task task;

    protected final LinkKafkaClient linkKafkaClient;
    protected final ContentKafkaClient contentKafkaClient;

    protected final ExecutorService executor = new ThreadPoolExecutor(MAX_THREAD, MAX_THREAD,
            0L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<>(MAX_QUEUE_SIZE));

    @Override
    public void run() {
        try (KafkaConsumer<String, Link> consumer = linkKafkaClient.createConsumer()) {
            while (!Thread.currentThread().isInterrupted() && !task.isFinish()) {
                ConsumerRecords<String, Link> records = consumer.poll(100);
                for (ConsumerRecord<String, Link> record : records) {
                    Link link = record.value();
                    executor.execute(new DownloadRunner(record, task) {
                        @Override
                        public void onStart() {
                            System.out.println(String.format("Start handling [%s]!", link.getUrl()));
                            //TODO: add logger
                        }

                        @Override
                        public void onSuccess(LinkContent content) {
                            System.out.println(String.format("Successfully handling [%s]!", link.getUrl()));
                            contentKafkaClient.send(content);
                        }

                        @Override
                        public void onFail(Exception e) {
                            //TODO: add logger
                            System.out.println(String.format("Error happens when handling [%s]!", link.getUrl()));
                            e.printStackTrace();
                        }

                        @Override
                        public void onFinish() {
                            //TODO: add Logger
                            System.out.println(String.format("Finish handling [%s]!", link.getUrl()));
                        }
                    });
                }
            }
        } finally {
            executor.shutdown();
        }
    }
}
