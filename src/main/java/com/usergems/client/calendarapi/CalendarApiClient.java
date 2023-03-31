package com.usergems.client.calendarapi;

public interface CalendarApiClient {
    CalendarInfoResponse getCalendarInfo(String email, int pageNum);

}
