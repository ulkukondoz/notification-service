package com.usergems.cache;

import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Map;
import java.util.Set;

@Component
public class SalesRepAccountLocalCache implements SalesRepAccountCache {
    private static final Map<String, String> tokenByEmail = Map.of(
            "stephan@usergems.com", "7S$16U^FmxkdV!1b",
            "christian@usergems.com", "Ay@T3ZwF3YN^fZ@M",
            "joss@usergems.com", "PK7UBPVeG%3pP9%B",
            "blaise@usergems.com", "c0R*4iQK21McwLww"
    );

    private static final Set<String> salesRep =  Collections.unmodifiableSet(tokenByEmail.keySet());

    @Override
    public String getToken(String email) {
        return tokenByEmail.get(email);
    }

    @Override
    public Set<String> getSalesReps() {
        return salesRep;
    }
}
