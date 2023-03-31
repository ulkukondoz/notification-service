package com.usergems.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class EmailNotification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    @Lob
    private String data;
    @Column(name="sent_at_timestamp", columnDefinition="TIMESTAMP")
    private LocalDateTime sentAtTimestamp;

    protected EmailNotification() {
        // for the framework
    }

    public EmailNotification(String data, LocalDateTime sentAtTimestamp) {
        this.data = data;
        this.sentAtTimestamp = sentAtTimestamp;
    }

    public long getId() {
        return id;
    }

    public String getData() {
        return data;
    }

    public LocalDateTime getSentAtTimestamp() {
        return sentAtTimestamp;
    }
}
