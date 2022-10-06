package com.xywei.springboot.demomongo;

import com.xywei.springboot.demomongo.entity.MergeRequest;
import com.xywei.springboot.demomongo.entity.TestUser;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class TestController {
    @Resource(name = "mongoTemplatePrimary")
    private MongoTemplate mongoTemplatePrimary;

    @Resource(name = "mongoTemplateAdmin")
    private MongoTemplate mongoTemplateAdmin;

    @GetMapping("/pk")
    public MergeRequest testFind() {
        MergeRequest mergeRequest = mongoTemplatePrimary.findById("633d97d27b6fbe75860e4c21", MergeRequest.class);
        System.out.println(mergeRequest);
        return mergeRequest;
    }

    @GetMapping("/admin")
    public TestUser testFind2() {
        return mongoTemplateAdmin.findById("633e7b9ddf226efab809741b", TestUser.class);
    }
}
