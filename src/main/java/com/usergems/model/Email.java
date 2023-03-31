package com.usergems.model;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

// TODO - json serialiser for LocalDateTime - using String for now
public class Email {
    private final String startTime;
    private final String endTime;
    private final Set<Client> clients;
    private final Set<String> salesReps;
    private final Set<CompanyInfo> companyInfo;

    // company table
    // person id
    // conmapny id
    // name, url, etc

    // person table
    // company id
    // fname, email, title, avatar

    // sales_rep
    //

    // email
    // id
    // starttme, end time , updated, statid_id
    // metadata - attachment/ video
    // list attendies
    // salesrep key - index




    public Email(String startTime, String endTime,
                 Set<CompanyInfo> companyInfo, Set<Client> attendees, Set<String> salesReps) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.companyInfo = companyInfo;
        this.clients = attendees;
        this.salesReps = salesReps;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public Set<Client> getClients() {
        return clients;
    }

    public Set<String> getSalesReps() {
        return salesReps;
    }

    public Set<CompanyInfo> getCompanyInfo() {
        return companyInfo;
    }

    public static class Client {
        private final String firstName;
        private final String lastName;
        private final String title;
        private final String imgUrl;
        private final String linkedinUrl;
        private final Map<String, AtomicInteger> numberOfTimesMetWithSaleRepBefore;
        private final boolean status;

        public Client(String firstName, String lastName, String title, String imgUrl, String linkedinUrl,
                      Map<String, AtomicInteger> numberOfTimesMetWithSaleRepBefore, boolean status) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.title = title;
            this.imgUrl = imgUrl;
            this.linkedinUrl = linkedinUrl;
            this.numberOfTimesMetWithSaleRepBefore = numberOfTimesMetWithSaleRepBefore;
            this.status = status;
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

        public Map<String, AtomicInteger> getNumberOfTimesMetWithSaleRepBefore() {
            return numberOfTimesMetWithSaleRepBefore;
        }

        public boolean isStatus() {
            return status;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Client client = (Client) o;
            return status == client.status && Objects.equals(firstName, client.firstName) && Objects.equals(lastName, client.lastName) && Objects.equals(title, client.title) && Objects.equals(imgUrl, client.imgUrl) && Objects.equals(linkedinUrl, client.linkedinUrl) && Objects.equals(numberOfTimesMetWithSaleRepBefore, client.numberOfTimesMetWithSaleRepBefore);
        }

        @Override
        public int hashCode() {
            return Objects.hash(firstName, lastName, title, imgUrl, linkedinUrl, numberOfTimesMetWithSaleRepBefore, status);
        }
    }

    public static class CompanyInfo {
        private final String companyName;
        private final String companyUrl;
        private final int numberOfEmployee;

        public CompanyInfo(String companyName, String companyUrl, int numberOfEmployee) {
            this.companyName = companyName;
            this.companyUrl = companyUrl;
            this.numberOfEmployee = numberOfEmployee;
        }

        public String getCompanyName() {
            return companyName;
        }

        public String getCompanyUrl() {
            return companyUrl;
        }

        public int getNumberOfEmployee() {
            return numberOfEmployee;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            CompanyInfo that = (CompanyInfo) o;
            return numberOfEmployee == that.numberOfEmployee && Objects.equals(companyName, that.companyName) && Objects.equals(companyUrl, that.companyUrl);
        }

        @Override
        public int hashCode() {
            return Objects.hash(companyName, companyUrl, numberOfEmployee);
        }
    }
}
