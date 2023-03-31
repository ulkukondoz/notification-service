package com.usergems.spring.configuration;

import com.usergems.notification.EmailConsumer;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class EmailConsumerConfig {
    @Autowired
    private EmailConsumer emailConsumer;

    @Bean
    public ExecutorService consumerExecutorService() {
        return Executors.newSingleThreadExecutor();
    }

    @PostConstruct
    public void init() {
        var executorService = consumerExecutorService();
        executorService.submit(emailConsumer::submitEmail);
    }
}
