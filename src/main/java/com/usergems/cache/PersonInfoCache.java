package com.usergems.cache;

import com.usergems.model.PersonInfo;

public interface PersonInfoCache {
    void update(String email, PersonInfo personInfo);

    PersonInfo getPersonInfo(String email);
}
