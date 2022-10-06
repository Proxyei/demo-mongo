package com.xywei.springboot.demomongo.config.mongodb;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
@ConfigurationProperties("mongodb.admin")
public class MongoDbConfigAdmin extends AbstractMongoDbConfig{

    @Bean(name = "mongoTemplateAdmin")
    @Override
    public MongoTemplate getMongoTemplate() {
        return new MongoTemplate(mongoDatabaseFactory());
    }
}
