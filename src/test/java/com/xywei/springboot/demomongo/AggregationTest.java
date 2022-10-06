package com.xywei.springboot.demomongo;

import com.alibaba.fastjson2.JSONObject;
import com.xywei.springboot.demomongo.entity.MergeRequest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;

import javax.annotation.Resource;

@SpringBootTest
public class AggregationTest {

    @Resource(name = "mongoTemplatePrimary")
    private MongoTemplate mongoTemplate;

    @Test
    public void testGroupByTargetBranch() {
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.group("targetBranch")
                        .sum("deleteLines").as("totalDeleteLines")
                        .sum("addLines").as("totalAddLines")
        );
        AggregationResults<JSONObject> result = mongoTemplate.aggregate(aggregation, MergeRequest.class, JSONObject.class);
        System.out.println(result.getMappedResults());
    }

}
