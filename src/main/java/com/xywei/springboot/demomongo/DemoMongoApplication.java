package com.xywei.springboot.demomongo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;

@SpringBootApplication(exclude = MongoAutoConfiguration.class)
public class DemoMongoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoMongoApplication.class, args);
    }

}
