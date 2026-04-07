package com.tienda.inventory.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.tienda.inventory.service.dto.InventoryDTO;
import com.tienda.inventory.service.dto.PurchaseDTO;
import com.tienda.inventory.service.dto.PurchaseRequestDTO;

/**
 * Service Interface for managing {@link com.tienda.inventory.domain.Inventory}.
 */
public interface InventoryService {
    /**
     * Save a inventory.
     *
     * @param inventoryDTO the entity to save.
     * @return the persisted entity.
     */
    InventoryDTO save(InventoryDTO inventoryDTO);

    /**
     * Updates a inventory.
     *
     * @param inventoryDTO the entity to update.
     * @return the persisted entity.
     */
    InventoryDTO update(InventoryDTO inventoryDTO);

    /**
     * Partially updates a inventory.
     *
     * @param inventoryDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<InventoryDTO> partialUpdate(InventoryDTO inventoryDTO);

    /**
     * Get all the inventories.
     *
     * @return the list of entities.
     */
    List<InventoryDTO> findAll();

        /**
     * Get the "id" inventory.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<InventoryDTO> findOne(String id);
    /**
     * Get the inventory by productId.
     *
     * @param productId the productId of the entity.
     * @return the entity.
     */
    Optional<InventoryDTO> findByProductId(Long productId);

    /**
     * Process a purchase.
     *
     * @param request the purchase request.
     * @param idempotencyKey the idempotency key.
     * @return the purchase DTO.
     */
    PurchaseDTO purchase(PurchaseRequestDTO request, String idempotencyKey);

    /**
     * Delete the "id" inventory.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
