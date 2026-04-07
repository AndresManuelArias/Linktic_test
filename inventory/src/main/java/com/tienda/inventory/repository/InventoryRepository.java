package com.tienda.inventory.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.tienda.inventory.domain.Inventory;

/**
 * Spring Data MongoDB repository for the Inventory entity.
 */
@Repository
public interface InventoryRepository extends MongoRepository<Inventory, String> {

    Optional<Inventory> findByProductId(Long productId);
}
