package com.tienda.inventory.service.impl;

import com.tienda.inventory.domain.Inventory;
import com.tienda.inventory.repository.InventoryRepository;
import com.tienda.inventory.service.InventoryService;
import com.tienda.inventory.service.dto.InventoryDTO;
import com.tienda.inventory.service.mapper.InventoryMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Service Implementation for managing {@link com.tienda.inventory.domain.Inventory}.
 */
@Service
public class InventoryServiceImpl implements InventoryService {

    private static final Logger LOG = LoggerFactory.getLogger(InventoryServiceImpl.class);

    private final InventoryRepository inventoryRepository;

    private final InventoryMapper inventoryMapper;

    public InventoryServiceImpl(InventoryRepository inventoryRepository, InventoryMapper inventoryMapper) {
        this.inventoryRepository = inventoryRepository;
        this.inventoryMapper = inventoryMapper;
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
    public void delete(String id) {
        LOG.debug("Request to delete Inventory : {}", id);
        inventoryRepository.deleteById(id);
    }
}
