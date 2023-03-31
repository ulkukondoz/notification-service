package com.usergems.spring.configuration;

import com.usergems.notification.EmailProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Component
public class EmailSchedulerConfig {

    private final EmailProducer emailProducer;

    @Autowired
    public EmailSchedulerConfig(EmailProducer emailProducer) {
        this.emailProducer = emailProducer;
    }

//    @Scheduled(cron = "0 0 8 * * *")
    @Scheduled(fixedRate = 5, timeUnit = TimeUnit.SECONDS)
    public void scheduleFixedRateTask() {
        ExecutorService service = Executors.newSingleThreadExecutor();
        service.submit(emailProducer::produceEmail);
    }
}
