package com.xywei.springboot.demomongo.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "MergeRequest")
public class MergeRequest {
    @Id
    private String id;
    private String projectId;
    private String repoUrl;
    private String sourceBranch;
    private Integer deleteLines = 0;
    private Integer addLines = 0;
    private String targetBranch;
    private String mergeStatus;
    private String mergeTime;
}
