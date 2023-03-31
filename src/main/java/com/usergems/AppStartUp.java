package com.usergems;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableJpaRepositories("com.usergems.dao")
@PropertySource("classpath:application.properties")
public class AppStartUp {
    public static void main(String[] args) {
        SpringApplication.run(AppStartUp.class, args);
    }

}
