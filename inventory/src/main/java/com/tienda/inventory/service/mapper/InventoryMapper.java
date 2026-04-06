package com.tienda.inventory.service.mapper;

import com.tienda.inventory.domain.Inventory;
import com.tienda.inventory.service.dto.InventoryDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Inventory} and its DTO {@link InventoryDTO}.
 */
@Mapper(componentModel = "spring")
public interface InventoryMapper extends EntityMapper<InventoryDTO, Inventory> {}
