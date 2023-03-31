package com.usergems.cache;

import com.usergems.model.CalendarInfo;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class CalendarLocalCache implements CalendarCache {
    private static final Map<String, Map<LocalDate, Map<Integer, CalendarInfo>>> cache = new ConcurrentHashMap<>();

    @Override
    public void save(String emailAddress, List<CalendarInfo> calendarInfos) {
        calendarInfos.forEach(info -> {
            var startDate = info.getStart().toLocalDate();
            int meetingId = info.getId();

            cache.computeIfAbsent(emailAddress, k -> new ConcurrentHashMap<>()).
                    computeIfAbsent(startDate, k -> new ConcurrentHashMap<>()).
                    put(meetingId, info);
        });
    }

    @Override
    public Collection<CalendarInfo> getCalendarInfo(String emailAddress, LocalDate date) {
        return Collections.unmodifiableCollection(cache.get(emailAddress).getOrDefault(date, Map.of()).values());
    }

}
