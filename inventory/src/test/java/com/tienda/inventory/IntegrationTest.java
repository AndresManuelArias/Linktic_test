package com.tienda.inventory;

import com.tienda.inventory.config.AsyncSyncConfiguration;
import com.tienda.inventory.config.JacksonConfiguration;
import com.tienda.inventory.config.MongoDbTestContainer;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.context.ImportTestcontainers;

/**
 * Base composite annotation for integration tests.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@SpringBootTest(classes = { InventoryApp.class, JacksonConfiguration.class, AsyncSyncConfiguration.class })
@ImportTestcontainers(MongoDbTestContainer.class)
public @interface IntegrationTest {}
