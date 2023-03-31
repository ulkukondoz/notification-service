package com.usergems.cache;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class MeetingTrackerLocalCache implements MeetingTrackerCache {
    private final static Map<String, Map<String, AtomicInteger>> tracker = new ConcurrentHashMap<>();

    @Override
    public void save(String client, String saleRep) {
        tracker.computeIfAbsent(client, k -> new HashMap<>())
                .computeIfAbsent(saleRep, k -> new AtomicInteger(0))
                .getAndIncrement();
    }

    @Override
    public Map<String, AtomicInteger> getTracking(String emailAddress) {
        return tracker.getOrDefault(emailAddress, Map.of());
    }
}
