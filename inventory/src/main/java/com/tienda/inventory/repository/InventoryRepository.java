package com.tienda.inventory.repository;

import com.tienda.inventory.domain.Inventory;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.UUID;

/**
 * Spring Data MongoDB repository for the Inventory entity.
 */
@Repository
public interface InventoryRepository extends MongoRepository<Inventory, String> {

    Optional<Inventory> findByProductId(UUID productId);
}
