package com.usergems.model;

import java.time.LocalDateTime;

public class PersonInfo {

    private final String firstName;
    private final String lastName;
    private final String title;
    private final String imgUrl;
    private final String linkedinUrl;
    private final Company company;
    private final LocalDateTime lastUpdated;

    public PersonInfo(String firstName, String lastName, String title, String imgUrl, String linkedinUrl,
                      Company company, LocalDateTime lastUpdated) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.title = title;
        this.imgUrl = imgUrl;
        this.linkedinUrl = linkedinUrl;
        this.company = company;
        this.lastUpdated = lastUpdated;
    }

    public static class Company {
        private final String name;
        private final String url;
        private final int numberOfEmployees;

        public Company(String name, String url, int numberOfEmployees) {
            this.name = name;
            this.url = url;
            this.numberOfEmployees = numberOfEmployees;
        }

        public String getName() {
            return name;
        }

        public String getUrl() {
            return url;
        }

        public int getNumberOfEmployees() {
            return numberOfEmployees;
        }
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getTitle() {
        return title;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public String getLinkedinUrl() {
        return linkedinUrl;
    }

    public Company getCompany() {
        return company;
    }

    public LocalDateTime getLastUpdated() {
        return lastUpdated;
    }
}
