package com.usergems.service;

import com.usergems.cache.MeetingTrackerCache;
import com.usergems.cache.SalesRepAccountCache;
import com.usergems.model.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@Service
public class EmailService {

    private final PersonInfoService personInfoService;
    private final CalendarService calendarInfoService;
    private final SalesRepAccountCache salesRepAccountCache;
    private final MeetingTrackerCache meetingTrackerCache;

    @Autowired
    public EmailService(PersonInfoService personInfoService, CalendarService calendarInfoService,
                        SalesRepAccountCache salesRepAccountCache, MeetingTrackerCache meetingTrackerCache) {
        this.personInfoService = personInfoService;
        this.calendarInfoService = calendarInfoService;
        this.salesRepAccountCache = salesRepAccountCache;
        this.meetingTrackerCache = meetingTrackerCache;
    }

    public List<Email> prepareEmail() {
        List<Email> emails = new LinkedList<>();
        var saleRepList = salesRepAccountCache.getSalesReps();

        saleRepList.forEach(saleRep -> {
            calendarInfoService.getCalendarInfo(saleRep, LocalDate.now()).forEach(calendarInfo -> {
                Set<Email.Client> clients = new HashSet<>();
                Set<String> salesRepInMeeting = new HashSet<>();
                Set<Email.CompanyInfo> companyInfo = new HashSet<>();

                calendarInfo.getAccepted().forEach(acceptedAttendeeEmail -> {
                    if (!saleRepList.contains(acceptedAttendeeEmail)) {
                        var personInfo = personInfoService.getPersonInfo(acceptedAttendeeEmail);
                        var company = personInfo.getCompany();

                        clients.add(new Email.Client(personInfo.getFirstName(), personInfo.getLastName(), personInfo.getTitle(),
                                personInfo.getImgUrl(), personInfo.getLinkedinUrl(), meetingTrackerCache.getTracking(acceptedAttendeeEmail),
                                true));
                        companyInfo.add(new Email.CompanyInfo(company.getName(), company.getUrl(), company.getNumberOfEmployees()));
                        meetingTrackerCache.save(acceptedAttendeeEmail, saleRep);
                    } else {
                        salesRepInMeeting.add(acceptedAttendeeEmail);
                    }
                });
                calendarInfo.getRejected().forEach(rejectedAttendeeEmail -> {
                    if (!saleRepList.contains(rejectedAttendeeEmail)) {
                        var personInfo = personInfoService.getPersonInfo(rejectedAttendeeEmail);
                        var company = personInfo.getCompany();

                        clients.add(new Email.Client(personInfo.getFirstName(), personInfo.getLastName(), personInfo.getTitle(),
                                personInfo.getImgUrl(), personInfo.getLinkedinUrl(), meetingTrackerCache.getTracking(rejectedAttendeeEmail),
                                false));
                        companyInfo.add(new Email.CompanyInfo(company.getName(), company.getUrl(), company.getNumberOfEmployees()));
                    } else {
                        salesRepInMeeting.add(rejectedAttendeeEmail);
                    }
                });

                emails.add(new Email(calendarInfo.getStart().toString(), calendarInfo.getEnd().toString(), companyInfo,
                        clients, salesRepInMeeting));

            });
        });

        // TODO sort by date?
        return emails;
    }
}
