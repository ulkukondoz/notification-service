package com.usergems.model;

import java.time.LocalDateTime;
import java.util.List;

public class CalendarInfo {
    private final int id;
    private final LocalDateTime changed;
    private final LocalDateTime start;
    private final LocalDateTime end;
    private final String tittle;
    private final List<String> accepted;
    private final List<String> rejected;

    public CalendarInfo(int id, LocalDateTime changed, LocalDateTime start, LocalDateTime end, String tittle, List<String> accepted, List<String> rejected) {
        this.id = id;
        this.changed = changed;
        this.start = start;
        this.end = end;
        this.tittle = tittle;
        this.accepted = accepted;
        this.rejected = rejected;
    }


    public int getId() {
        return id;
    }

    public LocalDateTime getChanged() {
        return changed;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public String getTittle() {
        return tittle;
    }

    public List<String> getAccepted() {
        return accepted;
    }

    public List<String> getRejected() {
        return rejected;
    }
}
