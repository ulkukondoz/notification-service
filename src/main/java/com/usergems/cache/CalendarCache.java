package com.usergems.cache;

import com.usergems.model.CalendarInfo;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

public interface CalendarCache {
    void save(String emailAddress, List<CalendarInfo> calendarInfos);

    Collection<CalendarInfo> getCalendarInfo(String emailAddress, LocalDate date);
}
