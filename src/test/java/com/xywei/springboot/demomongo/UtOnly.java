package com.xywei.springboot.demomongo;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.mongodb.MongoClientSettings;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class UtOnly {

    @Test
    void test1() {
        DateTime date = DateUtil.date();
        for (int i = 0; i < 20; i++) {
            DateTime dateTime = DateUtil.offsetSecond(date, new Random().nextInt(3600*24_30));
            System.out.println(dateTime.toString());
//            System.out.println(dateTime.toString("yyyy-MM-dd HH:mm:ss"));
//            System.out.println(new Random().nextInt(100));
        }
    }

    public void test2() {
        ServerAddress seed1 = new ServerAddress("host1", 27017);
        ServerAddress seed2 = new ServerAddress("host2", 27017);
        ServerAddress seed3 = new ServerAddress("host3", 27017);
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyToClusterSettings(builder ->
                        builder.hosts(Arrays.asList(seed1, seed2, seed3)))
                .build();
        MongoClient mongoClient = MongoClients.create(settings);
    }
}
