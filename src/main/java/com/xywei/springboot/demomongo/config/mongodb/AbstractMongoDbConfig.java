package com.xywei.springboot.demomongo.config.mongodb;

import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import lombok.Data;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Data
public abstract class AbstractMongoDbConfig {
    private String uri;
    private String username;
    private String password;
    private String database;
    private Map<String, Integer> address;
    private long serverSelectionTimeoutMs;
    private int connectTimeoutMs;
    private int maxSize;
    private int minSize;
    private long maxWaitTimeMs;
    private long maxConnectionIdleTime;

    public MongoDatabaseFactory mongoDatabaseFactory() {
        List<ServerAddress> serverAddresses = new ArrayList<>();
        address.forEach((host, port) -> {
            ServerAddress serverAddress = new ServerAddress(host, port);
            serverAddresses.add(serverAddress);
        });
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyToClusterSettings(builder -> {
                    builder.hosts(serverAddresses);
                    builder.serverSelectionTimeout(serverSelectionTimeoutMs, TimeUnit.MILLISECONDS);
                })
                .applyToSocketSettings(builder -> {
                    builder.connectTimeout(connectTimeoutMs, TimeUnit.MILLISECONDS);
                })
                .applyToConnectionPoolSettings(builder -> {
                    builder.maxSize(maxSize);
                    builder.minSize(minSize);
                    builder.maxWaitTime(maxWaitTimeMs, TimeUnit.MILLISECONDS);
                    builder.maxConnectionIdleTime(maxConnectionIdleTime, TimeUnit.MILLISECONDS);
                })
                .credential(MongoCredential.createCredential(username, database, password.toCharArray()))
                .build();
        MongoClient mongoClient = MongoClients.create(settings);
        return new SimpleMongoClientDatabaseFactory(mongoClient, database);
    }
    public abstract MongoTemplate getMongoTemplate();
}
