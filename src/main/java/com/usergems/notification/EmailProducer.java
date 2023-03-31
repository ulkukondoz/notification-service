package com.usergems.notification;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.usergems.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.concurrent.BlockingDeque;

@Component
public class EmailProducer {
    private final EmailService emailService;
    private final BlockingDeque<String> emailQueue;

    @Autowired
    public EmailProducer(EmailService emailService, @Qualifier("emailQueue") BlockingDeque<String> emailQueue) {
        this.emailService = emailService;
        this.emailQueue = emailQueue;
    }

    public void produceEmail() {
            var emails = emailService.prepareEmail();
            emails.forEach(email -> {
                try {
                    ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
                    String json = ow.writeValueAsString(emails);
                    emailQueue.add(json);
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
            });

    }
}
