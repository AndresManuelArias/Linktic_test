package com.tienda.inventory.service.mapper;

import com.tienda.inventory.domain.Purchase;
import com.tienda.inventory.service.dto.PurchaseDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Purchase} and its DTO {@link PurchaseDTO}.
 */
@Mapper(componentModel = "spring")
public interface PurchaseMapper extends EntityMapper<PurchaseDTO, Purchase> {}