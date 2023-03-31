package com.usergems.client.personapi;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.usergems.model.PersonInfo;

import java.time.LocalDateTime;

public class PersonInfoResponse {
    public String firstName;
    public String lastName;
    public String title;
    public String imgUrl;
    public String linkedinUrl;
    public CompanyResponse companyResponse;

    @JsonCreator
    public PersonInfoResponse(@JsonProperty("first_name") String firstName,
                              @JsonProperty("last_name") String lastName,
                              @JsonProperty("avatar") String imgUrl,
                              @JsonProperty("title") String title,
                              @JsonProperty("linkedin_url") String linkedinUrl,
                              @JsonProperty("company") CompanyResponse companyResponse
    ) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.imgUrl = imgUrl;
        this.title = title;
        this.linkedinUrl = linkedinUrl;
        this.companyResponse = companyResponse;
    }

    public static class CompanyResponse {
        public String name;
        public String url;
        public int numberOfEmployees;

        @JsonCreator
        public CompanyResponse(@JsonProperty("name") String name,
                               @JsonProperty("linkedin_url") String url,
                               @JsonProperty("employees") int numberOfEmployees
                               ) {
            this.name = name;
            this.url = url;
            this.numberOfEmployees = numberOfEmployees;
        }
    }

    public PersonInfo toPersonInfo() {
        return new PersonInfo(firstName, lastName, title, imgUrl, linkedinUrl,
                new PersonInfo.Company(companyResponse.name, companyResponse.url, companyResponse.numberOfEmployees),
                LocalDateTime.now());
    }
}
