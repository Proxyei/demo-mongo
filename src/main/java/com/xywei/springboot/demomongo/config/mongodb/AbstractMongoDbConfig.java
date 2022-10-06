package com.xywei.springboot.demomongo.config.mongodb;

import lombok.Data;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;

@Data
public abstract class AbstractMongoDbConfig {
    private String uri;
    private String username;
    private String password;
    private String database;
    public MongoDatabaseFactory mongoDatabaseFactory() {
        String connectionString = "mongodb://" + username + ":" + password+ "@"+ uri +"/" + database;
        return new SimpleMongoClientDatabaseFactory(connectionString);
    }
    public abstract MongoTemplate getMongoTemplate();
}
