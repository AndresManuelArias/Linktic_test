package com.tienda.inventory.service.dto;

import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.util.UUID;

/**
 * A DTO for purchase request.
 */
public class PurchaseRequestDTO implements Serializable {

    @NotNull
    private UUID productId;

    @NotNull
    @Min(value = 1)
    private Integer quantity;

    public UUID getProductId() {
        return productId;
    }

    public void setProductId(UUID productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}