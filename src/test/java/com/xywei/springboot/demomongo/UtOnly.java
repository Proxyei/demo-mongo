package com.xywei.springboot.demomongo;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import org.junit.jupiter.api.Test;

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
}
