package com.xywei.springboot.demomongo.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Document(collection = "TestData")
public class TestData {
    @Id
    private String id;
    @Field("addLines")
    private String addLines;
    @Field("del")
    private String del;
    private String branch;
    private String projectId;
}
