package cn.vgonet.mirror.test;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.containers.MySQLContainer;

@Slf4j
public class TestContainersInitializer implements BeforeAllCallback {
    private static MongoDBContainer mongodb;
    private static MySQLContainer<?> mysqldb;

    @Override
    public void beforeAll(ExtensionContext context) {
        log.info("Starting test containers...");
        startMongo();
        startMySQL();
    }

    private void startMongo() {
        if (mongodb != null) return;
        mongodb = new MongoDBContainer("mongo:4.0");
        mongodb.start();
        System.setProperty("MIRROR_DB_URI", mongodb.getReplicaSetUrl());
    }

    private void startMySQL() {
        if (mysqldb != null) return;
        mysqldb = new MySQLContainer<>("mysql:8.0");
        mysqldb.start();
        System.setProperty("SYNC_RESOURCE_DB_URI", mysqldb.getJdbcUrl());
    }
}
