package com.tienda.inventory.service;
import java.util.Optional;

import com.tienda.inventory.service.dto.PurchaseDTO;

/**
 * Service Interface for managing {@link com.tienda.inventory.domain.Purchase}.
 */
public interface PurchaseService {

    /**
     * Save a purchase.
     *
     * @param purchaseDTO the entity to save.
     * @return the persisted entity.
     */
    PurchaseDTO save(PurchaseDTO purchaseDTO);

    /**
     * Get the "id" purchase.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<PurchaseDTO> findById(String id);
}