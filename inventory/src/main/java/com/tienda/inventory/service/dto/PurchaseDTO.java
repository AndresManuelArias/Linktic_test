package com.tienda.inventory.service.dto;

import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

/**
 * A DTO for the {@link com.tienda.inventory.domain.Purchase} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class PurchaseDTO implements Serializable {

    private String id;

    @NotNull
    private UUID productId;

    @NotNull
    @Min(value = 1)
    private Integer quantity;

    private Instant createdAt;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PurchaseDTO)) {
            return false;
        }

        PurchaseDTO purchaseDTO = (PurchaseDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, purchaseDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PurchaseDTO{" +
            "id='" + getId() + "'" +
            ", productId=" + getProductId() +
            ", quantity=" + getQuantity() +
            ", createdAt='" + getCreatedAt() + "'" +
            "}";
    }
}