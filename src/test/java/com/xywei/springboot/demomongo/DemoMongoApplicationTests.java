package com.xywei.springboot.demomongo;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.google.common.collect.Lists;
import com.xywei.springboot.demomongo.entity.MergeRequest;
import com.xywei.springboot.demomongo.entity.TestUser;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.util.StopWatch;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

@SpringBootTest
class DemoMongoApplicationTests {

    @Resource(name = "mongoTemplatePrimary")
    private MongoTemplate mongoTemplatePrimary;

    @Resource(name = "mongoTemplateAdmin")
    private MongoTemplate mongoTemplateAdmin;

    @Test
    void contextLoads() {

        System.out.println(Math.random());

    }

    @Test
    public void testFind() {
        MergeRequest mergeRequest = mongoTemplatePrimary.findById("633d97d27b6fbe75860e4c21", MergeRequest.class);
        System.out.println(mergeRequest);
    }

    @Test
    public void testFind2() {
        System.out.println(mongoTemplateAdmin.findById("633e7b9ddf226efab809741b", TestUser.class));
    }

    public void testAddData() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        DateTime date = DateUtil.date();
        List<MergeRequest> batchToSave = new ArrayList<>();
        for (int i = 1; i <= 500_0000; i++) {
            int projectId = new Random().nextInt(100) + 1;
            MergeRequest mergeRequest = new MergeRequest();
            mergeRequest.setProjectId(String.valueOf(projectId));
            mergeRequest.setRepoUrl("/xywei/springboot/mongodb/" + projectId);
            int randomNumber1 = new Random().nextInt(400);
            DateTime mergeTime = DateUtil.offsetSecond(date, new Random().nextInt(3600*24_40));;
            mergeRequest.setMergeTime(mergeTime.toString());
            int randomNumber2 = new Random().nextInt(100);
            mergeRequest.setSourceBranch("dev" + randomNumber2);
            int randomNumber3 = new Random().nextInt(100);
            mergeRequest.setTargetBranch("master" + randomNumber3);
            mergeRequest.setAddLines(randomNumber1);
            int randomNumber4 = new Random().nextInt(400);
            mergeRequest.setDeleteLines(randomNumber4);
            if (projectId / 2 == 0) {
                mergeRequest.setMergeStatus("merged");
            } else {
                mergeRequest.setMergeStatus("open");
            }
            batchToSave.add(mergeRequest);
        }
        stopWatch.stop();
        System.out.println("花费时间1：" + stopWatch.getTotalTimeSeconds());
        stopWatch.start();
        for (List<MergeRequest> mergeRequests : Lists.partition(batchToSave, 500000)) {
            Collection<MergeRequest> insert = mongoTemplatePrimary.insert(mergeRequests, MergeRequest.class);
            System.out.println("插入：" + insert.size());
        }
        stopWatch.stop();
        System.out.println("花费时间2：" + stopWatch.getTotalTimeSeconds());
    }
}
