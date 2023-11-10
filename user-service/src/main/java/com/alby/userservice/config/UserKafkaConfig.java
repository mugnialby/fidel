package com.alby.userservice.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class UserKafkaConfig {
    
    @Value("${kafka.topic.user.events}")
    private String userEventsTopic;
    
    @Bean
    public NewTopic userEvents() {
        return TopicBuilder.name(userEventsTopic)
            .partitions(1)
            .build();
    }
}
