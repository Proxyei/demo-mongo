package com.xywei.springboot.demomongo.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Data
@Document(collection = "testUser")
public class TestUser {
    @Id
    private String id;
    private String name;
    private String school;
    private String grade;
    @Field("class")
    private String clazz;
    private List<String> hobbies;
    private String joinTime;
}
