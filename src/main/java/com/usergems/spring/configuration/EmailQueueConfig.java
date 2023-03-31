package com.usergems.spring.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

@Configuration
public class EmailQueueConfig {
    @Bean
    public BlockingDeque<String> emailQueue() {
        return new LinkedBlockingDeque<>();
    }
}
