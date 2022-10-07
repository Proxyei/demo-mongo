package com.xywei.springboot.demomongo;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.google.common.collect.Lists;
import com.mongodb.MongoClientSettings;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.stream.Stream;

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

    @Test
    public void test2() {
        Lists.newArrayList(1,3,5,7,9).forEach(data -> {
            if (!(data instanceof Integer)) {
                return;
            }
            System.out.println(data);
        });
    }
}
