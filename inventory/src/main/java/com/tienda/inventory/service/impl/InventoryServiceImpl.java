package com.tienda.inventory.service.impl;

import com.tienda.inventory.client.ProductsClient;
import com.tienda.inventory.domain.Inventory;
import com.tienda.inventory.repository.InventoryRepository;
import com.tienda.inventory.service.InventoryService;
import com.tienda.inventory.service.PurchaseService;
import com.tienda.inventory.service.dto.InventoryDTO;
import com.tienda.inventory.service.dto.PurchaseDTO;
import com.tienda.inventory.service.dto.PurchaseRequestDTO;
import com.tienda.inventory.service.mapper.InventoryMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

/**
 * Service Implementation for managing {@link com.tienda.inventory.domain.Inventory}.
 */
@Service
public class InventoryServiceImpl implements InventoryService {

    private static final Logger LOG = LoggerFactory.getLogger(InventoryServiceImpl.class);

    private final InventoryRepository inventoryRepository;

    private final InventoryMapper inventoryMapper;

    private final PurchaseService purchaseService;

    private final ProductsClient productsClient;

    public InventoryServiceImpl(InventoryRepository inventoryRepository, InventoryMapper inventoryMapper, PurchaseService purchaseService, ProductsClient productsClient) {
        this.inventoryRepository = inventoryRepository;
        this.inventoryMapper = inventoryMapper;
        this.purchaseService = purchaseService;
        this.productsClient = productsClient;
    }

    @Override
    public InventoryDTO save(InventoryDTO inventoryDTO) {
        LOG.debug("Request to save Inventory : {}", inventoryDTO);
        Inventory inventory = inventoryMapper.toEntity(inventoryDTO);
        inventory = inventoryRepository.save(inventory);
        return inventoryMapper.toDto(inventory);
    }

    @Override
    public InventoryDTO update(InventoryDTO inventoryDTO) {
        LOG.debug("Request to update Inventory : {}", inventoryDTO);
        Inventory inventory = inventoryMapper.toEntity(inventoryDTO);
        inventory = inventoryRepository.save(inventory);
        return inventoryMapper.toDto(inventory);
    }

    @Override
    public Optional<InventoryDTO> partialUpdate(InventoryDTO inventoryDTO) {
        LOG.debug("Request to partially update Inventory : {}", inventoryDTO);

        return inventoryRepository
            .findById(inventoryDTO.getId())
            .map(existingInventory -> {
                inventoryMapper.partialUpdate(existingInventory, inventoryDTO);

                return existingInventory;
            })
            .map(inventoryRepository::save)
            .map(inventoryMapper::toDto);
    }

    @Override
    public List<InventoryDTO> findAll() {
        LOG.debug("Request to get all Inventories");
        return inventoryRepository.findAll().stream().map(inventoryMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    public Optional<InventoryDTO> findOne(String id) {
        LOG.debug("Request to get Inventory : {}", id);
        return inventoryRepository.findById(id).map(inventoryMapper::toDto);
    }

    @Override
    public Optional<InventoryDTO> findByProductId(Long productId) {
        LOG.debug("Request to get Inventory by productId : {}", productId);
        return inventoryRepository.findByProductId(productId).map(inventoryMapper::toDto);
    }

    @Override
    @Transactional
    public PurchaseDTO purchase(PurchaseRequestDTO request, String idempotencyKey) {
        LOG.debug("Request to purchase : {}, key: {}", request, idempotencyKey);

        // Check idempotency
        Optional<PurchaseDTO> existingPurchase = purchaseService.findById(idempotencyKey);
        if (existingPurchase.isPresent()) {
            return existingPurchase.get();
        }

        // Validate product exists
        try {
            var response = productsClient.getProduct(request.getProductId());
            if (!response.getStatusCode().is2xxSuccessful()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product not found");
            }
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product not found");
        }

        // Get inventory
        Inventory inventory = inventoryRepository.findByProductId(request.getProductId())
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Inventory not found"));

        // Check stock
        if (inventory.getAvailable() < request.getQuantity()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Insufficient stock");
        }

        // Decrement stock
        inventory.setAvailable(inventory.getAvailable() - request.getQuantity());
        inventoryRepository.save(inventory);

        // Save purchase
        PurchaseDTO purchaseDTO = new PurchaseDTO();
        purchaseDTO.setId(idempotencyKey);
        purchaseDTO.setProductId(request.getProductId());
        purchaseDTO.setQuantity(request.getQuantity());
        return purchaseService.save(purchaseDTO);
    }

    @Override
    public void delete(String id) {
        LOG.debug("Request to delete Inventory : {}", id);
        inventoryRepository.deleteById(id);
    }
}
