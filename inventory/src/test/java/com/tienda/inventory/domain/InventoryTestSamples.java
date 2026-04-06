package com.tienda.inventory.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class InventoryTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2L * Integer.MAX_VALUE));
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static Inventory getInventorySample1() {
        return new Inventory()
            .id("id1")
            .productId(UUID.fromString("23d8dc04-a48b-45d9-a01d-4b728f0ad4aa"))
            .available(1)
            .reserved(1)
            .version(1L);
    }

    public static Inventory getInventorySample2() {
        return new Inventory()
            .id("id2")
            .productId(UUID.fromString("ad79f240-3727-46c3-b89f-2cf6ebd74367"))
            .available(2)
            .reserved(2)
            .version(2L);
    }

    public static Inventory getInventoryRandomSampleGenerator() {
        return new Inventory()
            .id(UUID.randomUUID().toString())
            .productId(UUID.randomUUID())
            .available(intCount.incrementAndGet())
            .reserved(intCount.incrementAndGet())
            .version(longCount.incrementAndGet());
    }
}
