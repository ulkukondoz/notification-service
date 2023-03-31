package com.usergems.service;

import com.usergems.cache.CalendarCache;
import com.usergems.cache.MeetingTrackerCache;
import com.usergems.cache.SalesRepAccountCache;
import com.usergems.client.calendarapi.CalendarApiClient;
import com.usergems.client.calendarapi.CalendarInfoResponse;
import com.usergems.model.CalendarInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CalendarService {
    Logger logger = LoggerFactory.getLogger(CalendarService.class);

    private final CalendarApiClient client;
    private final CalendarCache calendarCache;
    private final SalesRepAccountCache salesRepAccountCache;
    private final MeetingTrackerCache meetingTrackerCache;

    @Autowired
    public CalendarService(CalendarApiClient client, CalendarCache calendarCache,
                           SalesRepAccountCache salesRepAccountCache, MeetingTrackerCache meetingTrackerCache) {
        this.client = client;
        this.calendarCache = calendarCache;
        this.salesRepAccountCache = salesRepAccountCache;
        this.meetingTrackerCache = meetingTrackerCache;

        // cache warm up
        warmUpCaches();
    }

    private void warmUpCaches() {
        var saleReps = salesRepAccountCache.getSalesReps();
        saleReps.forEach(salesRep -> {
            var calendarInfo = getAllCalendarInfo(salesRep);
            calendarCache.save(salesRep, calendarInfo);

            calendarInfo.forEach(info -> info.getAccepted().forEach(attendee -> {
                if (!saleReps.contains(attendee)) {
                    meetingTrackerCache.save(attendee, salesRep);
                }
            }));
        });
    }

    private void updateLatestChanges(String email) {
        var pageNum = 1;
        var numberOfPageToRequest = 1;
        var today = LocalDate.now();
        List<CalendarInfo> calendarInfo = new LinkedList<>();

        do {
            var calendarInfoResponse = client.getCalendarInfo(email, pageNum);
            var dataResponse = calendarInfoResponse.data;

            numberOfPageToRequest = calculateNumberOfPage(calendarInfoResponse.total, calendarInfoResponse.perPage);

            calendarInfo.addAll(dataResponse.stream()
                    .map(CalendarInfoResponse.DataResponse::toCalendarInfo)
                    .collect(Collectors.toList()));

            // get last calendar data to check if it is in the past, no need to process yesterday meetings
            var lastData = dataResponse.get(dataResponse.size() - 1);
            if (lastData.changed.toLocalDate().isBefore(today)) {
                break;
            }

            pageNum++;

        } while (pageNum == numberOfPageToRequest);

        calendarCache.save(email, calendarInfo);
    }

    public Collection<CalendarInfo> getCalendarInfo(String emailAddress, LocalDate date) {
        updateLatestChanges(emailAddress);
        return calendarCache.getCalendarInfo(emailAddress, date);
    }

    private List<CalendarInfo> getAllCalendarInfo(String email) {
        var pageNum = 1;
        var numberOfPageToRequest = 1;
        List<CalendarInfo> calendarInfo = new LinkedList<>();

        do {
            var calendarInfoResponse = client.getCalendarInfo(email, pageNum);
            var dataResponse = calendarInfoResponse.data;

            numberOfPageToRequest = calculateNumberOfPage(calendarInfoResponse.total, calendarInfoResponse.perPage);

            calendarInfo.addAll(dataResponse.stream()
                    .map(CalendarInfoResponse.DataResponse::toCalendarInfo)
                    .collect(Collectors.toList()));

            pageNum++;

        } while (pageNum == numberOfPageToRequest);

        return calendarInfo;
    }

    private static int calculateNumberOfPage(int total, int perPage) {
        var modulus = total % perPage;
        return total / perPage + (modulus > 0 ? 1 : 0);
    }
}
