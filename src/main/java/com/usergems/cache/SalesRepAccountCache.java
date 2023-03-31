package com.usergems.cache;

import java.util.Set;


public interface SalesRepAccountCache {

    String getToken(String email);

    Set<String> getSalesReps();
}
