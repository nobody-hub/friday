package com.nobodyhub.friday.crawler.person;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @author Ryan
 * @since 30/01/2018
 */
@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
public class PersonMain {
    public static void main(String[] args) {
        System.exit(SpringApplication.exit(SpringApplication.run(PersonMain.class, args)));
    }
}
