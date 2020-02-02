package com.example.springbootmail;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;


@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class SpringbootMailApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootMailApplication.class, args);
    }


}
