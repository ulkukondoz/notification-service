package com.usergems.service;

import com.usergems.cache.PersonInfoCache;
import com.usergems.client.personapi.PersonApiClient;
import com.usergems.model.PersonInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@ConfigurationProperties(prefix = "person.cache")
public class PersonInfoService {
    private final int ttlInDays;
    private final PersonApiClient personInfoClient;
    private final PersonInfoCache cache;

    @Autowired
    public PersonInfoService(@Value("${person.cache.ttl.in.days}") int ttlInDays, PersonApiClient personInfoClient, PersonInfoCache cache) {
        this.ttlInDays = ttlInDays;
        this.personInfoClient = personInfoClient;
        this.cache = cache;
    }

    public PersonInfo getPersonInfo(String email) {
        var personInfo = cache.getPersonInfo(email);

        if (personInfo == null || isStale(personInfo)) {
            personInfo = personInfoClient.getPersonInfo(email).toPersonInfo();
            cache.update(email, personInfo);
        }
        return personInfo;
    }

    private boolean isStale(PersonInfo personInfo) {
        return personInfo.getLastUpdated().plusDays(ttlInDays).isBefore(LocalDateTime.now());
    }
}
