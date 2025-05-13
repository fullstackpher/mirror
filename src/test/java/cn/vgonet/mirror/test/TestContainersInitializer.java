package cn.vgonet.mirror.test;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.testcontainers.containers.MongoDBContainer;

@Slf4j
public class TestContainersInitializer implements BeforeAllCallback {
    private static MongoDBContainer mongodb;

    @Override
    public void beforeAll(ExtensionContext context) {
        log.info("Starting test containers...");
        startMongo();
    }

    private void startMongo() {
        if (mongodb != null) return;
        mongodb = new MongoDBContainer("mongo:4.0");
        mongodb.start();
        System.setProperty("ARCHEGOS_DB_URI", mongodb.getReplicaSetUrl());
    }
}
