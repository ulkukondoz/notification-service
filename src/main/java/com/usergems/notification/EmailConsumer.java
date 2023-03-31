package com.usergems.notification;

import com.usergems.dao.EmailRepository;
import com.usergems.model.EmailNotification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.concurrent.BlockingDeque;

@Component
public class EmailConsumer {
    private final BlockingDeque<String> emailQueue;
    private final EmailRepository repository;


    @Autowired
    public EmailConsumer(@Qualifier("emailQueue") BlockingDeque<String> emailQueue,  EmailRepository repository) {
        this.emailQueue = emailQueue;
        this.repository = repository;
    }

    public void submitEmail() {
        while (true) {
            try {
                var email = emailQueue.take();
                var emailNotification = new EmailNotification(email, LocalDateTime.now());
                repository.save(emailNotification);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
