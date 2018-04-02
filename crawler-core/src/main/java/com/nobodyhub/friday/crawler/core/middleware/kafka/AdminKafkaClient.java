package com.nobodyhub.friday.crawler.core.middleware.kafka;

import com.google.common.collect.Lists;
import com.nobodyhub.friday.crawler.core.execution.TaskMetadata;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;

import java.util.List;
import java.util.Properties;

/**
 *
 * @author Ryan
 */
public class AdminKafkaClient {

    private static final AdminKafkaClient instance = new AdminKafkaClient();

    public static AdminKafkaClient getInstance() {
        return instance;
    }

    private final AdminClient adminClient;

    private AdminKafkaClient() {
        Properties props = new Properties();
        props.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        adminClient = AdminClient.create(props);
    }

    public synchronized void listTopics() {
        adminClient.listTopics();
    }

    public void createTopics(TaskMetadata metadata) {
        String[] topics = new String[3];
        topics[0] = metadata.getLinkTopic();
        topics[1] = metadata.getContentTopic();
        topics[2] = metadata.getItemTopic();
        createTopics(topics);
    }


    public synchronized void createTopics(String... topics) {
        List<NewTopic> newTopics = Lists.newArrayList();
        for (String topic : topics) {
            newTopics.add(new NewTopic(topic, 1, (short) 1));
        }
        adminClient.createTopics(newTopics);
    }

    public synchronized void deleteTopics(String... topics) {
        adminClient.deleteTopics(Lists.newArrayList(topics));
    }
}
