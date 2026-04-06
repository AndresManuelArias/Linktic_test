package com.tienda.inventory.domain;

import jakarta.validation.constraints.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * A Inventory.
 */
@Document(collection = "inventory")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Inventory implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @NotNull
    @Field("product_id")
    private UUID productId;

    @NotNull
    @Min(value = 0)
    @Field("available")
    private Integer available;

    @Field("reserved")
    private Integer reserved;

    @Field("version")
    private Long version;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getId() {
        return this.id;
    }

    public Inventory id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public UUID getProductId() {
        return this.productId;
    }

    public Inventory productId(UUID productId) {
        this.setProductId(productId);
        return this;
    }

    public void setProductId(UUID productId) {
        this.productId = productId;
    }

    public Integer getAvailable() {
        return this.available;
    }

    public Inventory available(Integer available) {
        this.setAvailable(available);
        return this;
    }

    public void setAvailable(Integer available) {
        this.available = available;
    }

    public Integer getReserved() {
        return this.reserved;
    }

    public Inventory reserved(Integer reserved) {
        this.setReserved(reserved);
        return this;
    }

    public void setReserved(Integer reserved) {
        this.reserved = reserved;
    }

    public Long getVersion() {
        return this.version;
    }

    public Inventory version(Long version) {
        this.setVersion(version);
        return this;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Inventory)) {
            return false;
        }
        return getId() != null && getId().equals(((Inventory) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Inventory{" +
            "id=" + getId() +
            ", productId='" + getProductId() + "'" +
            ", available=" + getAvailable() +
            ", reserved=" + getReserved() +
            ", version=" + getVersion() +
            "}";
    }
}
