package com.usergems.cache;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public interface MeetingTrackerCache {
    void save(String client, String salesRep);

    Map<String, AtomicInteger> getTracking(String emailAddress);
}
