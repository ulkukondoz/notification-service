package com.usergems.cache;

import com.usergems.model.PersonInfo;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class PersonInfoLocalCache implements PersonInfoCache {

    private static final Map<String, PersonInfo> cache = new ConcurrentHashMap<>();

    @Override
    public void update(String email, PersonInfo personInfo) {
        cache.put(email, personInfo);
    }

    @Override
    public PersonInfo getPersonInfo(String email) {
        return cache.get(email);
    }


}
