package com.alby.userservice.config;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class UserKafkaConfig {

    @Bean
    public NewTopic userEvents() {
        return TopicBuilder.name("users.events")
            .partitions(1)
            .build();
    }
}
