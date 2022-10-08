package com.xywei.springboot.demomongo;

import com.alibaba.fastjson2.JSONObject;
import com.xywei.springboot.demomongo.entity.MergeRequest;
import com.xywei.springboot.demomongo.entity.TestData;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.ConvertOperators;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import javax.annotation.Resource;
import java.util.List;

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


    @Test
    public void testConvert() {
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.match(Criteria.where("branch").is("dev")),
                Aggregation.project("projectId").and(
                        ConvertOperators.Convert.convertValueOf("addLines").to("int").onErrorReturn(0).onNullReturn(0)
                ).as("totalAdd").and(
                        ConvertOperators.Convert.convertValueOf("del").to("int").onErrorReturn(0).onNullReturn(0)
                ).as("totalDel"),
                Aggregation.group("projectId").sum("totalAdd").as("addResult").sum("totalDel").as("delResult"),
                Aggregation.project("addResult", "delResult", "projectId")
        );
        AggregationResults<JSONObject> result = mongoTemplate.aggregate(aggregation, TestData.class, JSONObject.class);
        System.out.println(result.getMappedResults());
        // {
        //   "_id": {
        //     "$oid": "634165ccbe807d1853fcd075"
        //   },
        //   "del": "2",
        //   "addLines": "1",
        //   "branch": "dev",
        //   "date": "2012-01-01 12:12:12",
        //   "projectId": "1"
        // }

        // {
        //  "_id": {
        //    "$oid": "63416765be807d1853fcd076"
        //  },
        //  "del": "4",
        //  "addLines": "3",
        //  "branch": "dev",
        //  "date": "2011-11-11 11:00:00.999",
        //  "projectId": "1"
        //}

    }

}
