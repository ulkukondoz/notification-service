package com.usergems.client.calendarapi;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.usergems.model.CalendarInfo;

import java.time.LocalDateTime;
import java.util.List;

public class CalendarInfoResponse {
    public int total;
    public int perPage;
    public int currentPage;
    public List<DataResponse> data;

    @JsonCreator
    public CalendarInfoResponse(@JsonProperty("total") int total,
                                @JsonProperty("per_page") int perPage,
                                @JsonProperty("current_page") int currentPage,
                                @JsonProperty("data") List<DataResponse> data) {
        this.total = total;
        this.perPage = perPage;
        this.currentPage = currentPage;
        this.data = data;
    }

    public static class DataResponse {
        public int id;
        public LocalDateTime changed;
        public LocalDateTime start;
        public LocalDateTime end;
        public String tittle;
        public List<String> accepted;
        public List<String> rejected;

        @JsonCreator
        public DataResponse(@JsonProperty("id") int id,
                            @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
                            @JsonProperty("changed") LocalDateTime changed,
                            @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") @JsonProperty("start") LocalDateTime start,
                            @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") @JsonProperty("end") LocalDateTime end,
                            @JsonProperty("accepted") List<String> accepted,
                            @JsonProperty("rejected") List<String> rejected) {
            this.id = id;
            this.changed = changed;
            this.start = start;
            this.end = end;
            this.accepted = accepted;
            this.rejected = rejected;
        }

        public CalendarInfo toCalendarInfo() {
            return new CalendarInfo(id, changed, start, end, tittle, accepted, rejected);
        }
    }
}
