package com.usergems.client.personapi;

import com.usergems.cache.SalesRepAccountCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@ConfigurationProperties(prefix = "person.api")
public class PersonApiCaller implements PersonApiClient {

    private final String baseUrl;
    private final SalesRepAccountCache tokenCache;
    private final RestTemplate restTemplate;

    @Autowired
    public PersonApiCaller(@Value("${person.api.baseUrl}") String baseUrl, SalesRepAccountCache tokenCache) {
        this.baseUrl = baseUrl;
        this.restTemplate = new RestTemplate();
        this.tokenCache = tokenCache;
    }

    @Override
    public PersonInfoResponse getPersonInfo(String emailAddress) {
        HttpHeaders headers = new HttpHeaders();
        // TODO - need to know client token, hardcoding for now
//        headers.setBearerAuth(tokenCache.getToken(emailAddress));
        headers.setBearerAuth("9d^XItOjTAGSG5ch");
        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
        var url = baseUrl + emailAddress;
        return restTemplate.exchange(url, HttpMethod.GET, entity, PersonInfoResponse.class).getBody();
    }
}
