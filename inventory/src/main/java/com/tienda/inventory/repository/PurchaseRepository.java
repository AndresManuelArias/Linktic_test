package com.tienda.inventory.repository;

import com.tienda.inventory.domain.Purchase;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the Purchase entity.
 */
@Repository
public interface PurchaseRepository extends MongoRepository<Purchase, String> {}