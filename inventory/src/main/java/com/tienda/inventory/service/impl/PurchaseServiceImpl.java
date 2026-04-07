package com.tienda.inventory.service.impl;

import com.tienda.inventory.domain.Purchase;
import com.tienda.inventory.repository.PurchaseRepository;
import com.tienda.inventory.service.PurchaseService;
import com.tienda.inventory.service.dto.PurchaseDTO;
import com.tienda.inventory.service.mapper.PurchaseMapper;
import java.time.Instant;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Service Implementation for managing {@link com.tienda.inventory.domain.Purchase}.
 */
@Service
public class PurchaseServiceImpl implements PurchaseService {

    private static final Logger LOG = LoggerFactory.getLogger(PurchaseServiceImpl.class);

    private final PurchaseRepository purchaseRepository;

    private final PurchaseMapper purchaseMapper;

    public PurchaseServiceImpl(PurchaseRepository purchaseRepository, PurchaseMapper purchaseMapper) {
        this.purchaseRepository = purchaseRepository;
        this.purchaseMapper = purchaseMapper;
    }

    @Override
    public PurchaseDTO save(PurchaseDTO purchaseDTO) {
        LOG.debug("Request to save Purchase : {}", purchaseDTO);
        Purchase purchase = purchaseMapper.toEntity(purchaseDTO);
        if (purchase.getCreatedAt() == null) {
            purchase.setCreatedAt(Instant.now());
        }
        purchase = purchaseRepository.save(purchase);
        return purchaseMapper.toDto(purchase);
    }

    @Override
    public Optional<PurchaseDTO> findById(String id) {
        LOG.debug("Request to get Purchase : {}", id);
        return purchaseRepository.findById(id).map(purchaseMapper::toDto);
    }
}