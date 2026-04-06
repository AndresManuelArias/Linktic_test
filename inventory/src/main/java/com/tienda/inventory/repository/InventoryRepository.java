package com.tienda.inventory.repository;

import com.tienda.inventory.domain.Inventory;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the Inventory entity.
 */
@Repository
public interface InventoryRepository extends MongoRepository<Inventory, String> {}
