package com.usergems.client.calendarapi;

import com.usergems.cache.SalesRepAccountCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.Map;

@Component
@ConfigurationProperties(prefix = "calendar.api")
public class CalendarApiCaller implements CalendarApiClient {
    private final String baseUrl;
    private final SalesRepAccountCache tokenCache;
    private final RestTemplate restTemplate;


    @Autowired
    public CalendarApiCaller(@Value("${calendar.api.baseUrl}") String baseUrl, SalesRepAccountCache tokenCache) {
        this.baseUrl = baseUrl;
        this.tokenCache = tokenCache;
        this.restTemplate = new RestTemplate();
    }

    @Override
    public CalendarInfoResponse getCalendarInfo(String email, int pageNum) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(tokenCache.getToken(email));
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<>("body", headers);
        Map<String , Integer> params = Map.of("page", pageNum);
        return restTemplate.exchange(baseUrl, HttpMethod.GET, entity, CalendarInfoResponse.class, params).getBody();
    }
}
