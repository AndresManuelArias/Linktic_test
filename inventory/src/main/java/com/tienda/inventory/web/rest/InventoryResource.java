package com.tienda.inventory.web.rest;

import java.util.UUID;
import com.tienda.inventory.client.ProductsClient;
import com.tienda.inventory.repository.InventoryRepository;
import com.tienda.inventory.service.InventoryService;
import com.tienda.inventory.service.PurchaseService;
import com.tienda.inventory.service.dto.InventoryDTO;
import com.tienda.inventory.service.dto.PurchaseDTO;
import com.tienda.inventory.service.dto.PurchaseRequestDTO;
import com.tienda.inventory.web.rest.errors.BadRequestAlertException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.tienda.inventory.domain.Inventory}.
 */
@RestController
@RequestMapping("/api/inventories")
public class InventoryResource {

    private static final Logger LOG = LoggerFactory.getLogger(InventoryResource.class);

    private static final String ENTITY_NAME = "inventoryInventory";

    @Value("${jhipster.clientApp.name:inventory}")
    private String applicationName;

    private final InventoryService inventoryService;

    private final InventoryRepository inventoryRepository;

    private final ProductsClient productsClient;

    private final PurchaseService purchaseService;

    public InventoryResource(InventoryService inventoryService, InventoryRepository inventoryRepository, ProductsClient productsClient, PurchaseService purchaseService) {
        this.inventoryService = inventoryService;
        this.inventoryRepository = inventoryRepository;
        this.productsClient = productsClient;
        this.purchaseService = purchaseService;
    }

    /**
     * {@code POST  /inventories} : Create a new inventory.
     *
     * @param inventoryDTO the inventoryDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new inventoryDTO, or with status {@code 400 (Bad Request)} if the inventory has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<InventoryDTO> createInventory(@Valid @RequestBody InventoryDTO inventoryDTO) throws URISyntaxException {
        LOG.debug("REST request to save Inventory : {}", inventoryDTO);
        if (inventoryDTO.getId() != null) {
            throw new BadRequestAlertException("A new inventory cannot already have an ID", ENTITY_NAME, "idexists");
        }
        inventoryDTO = inventoryService.save(inventoryDTO);
        return ResponseEntity.created(new URI("/api/inventories/" + inventoryDTO.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, inventoryDTO.getId()))
            .body(inventoryDTO);
    }

    /**
     * {@code PUT  /inventories/:id} : Updates an existing inventory.
     *
     * @param id the id of the inventoryDTO to save.
     * @param inventoryDTO the inventoryDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated inventoryDTO,
     * or with status {@code 400 (Bad Request)} if the inventoryDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the inventoryDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<InventoryDTO> updateInventory(
        @PathVariable(value = "id", required = false) final String id,
        @Valid @RequestBody InventoryDTO inventoryDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to update Inventory : {}, {}", id, inventoryDTO);
        if (inventoryDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, inventoryDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!inventoryRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        inventoryDTO = inventoryService.update(inventoryDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, inventoryDTO.getId()))
            .body(inventoryDTO);
    }

    /**
     * {@code PATCH  /inventories/:id} : Partial updates given fields of an existing inventory, field will ignore if it is null
     *
     * @param id the id of the inventoryDTO to save.
     * @param inventoryDTO the inventoryDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated inventoryDTO,
     * or with status {@code 400 (Bad Request)} if the inventoryDTO is not valid,
     * or with status {@code 404 (Not Found)} if the inventoryDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the inventoryDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<InventoryDTO> partialUpdateInventory(
        @PathVariable(value = "id", required = false) final String id,
        @NotNull @RequestBody InventoryDTO inventoryDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update Inventory partially : {}, {}", id, inventoryDTO);
        if (inventoryDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, inventoryDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!inventoryRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<InventoryDTO> result = inventoryService.partialUpdate(inventoryDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, inventoryDTO.getId())
        );
    }

    /**
     * {@code GET  /inventories} : get all the Inventories.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of Inventories in body.
     */
    @GetMapping("")
    public List<InventoryDTO> getAllInventories() {
        LOG.debug("REST request to get all Inventories");
        return inventoryService.findAll();
    }

    /**
     * {@code GET  /inventories/products/:productId} : get the inventory for the product.
     *
     * @param productId the productId of the inventoryDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the inventoryDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/products/{productId}")
    public ResponseEntity<InventoryDTO> getInventoryByProduct(@PathVariable("productId") UUID productId) {
        LOG.debug("REST request to get Inventory by productId : {}", productId);
        // Validate product exists
        try {
            ResponseEntity<Void> response = productsClient.getProduct(productId);
            if (!response.getStatusCode().is2xxSuccessful()) {
                throw new BadRequestAlertException("Product not found", ENTITY_NAME, "productnotfound");
            }
        } catch (Exception e) {
            throw new BadRequestAlertException("Product not found", ENTITY_NAME, "productnotfound");
        }
        Optional<InventoryDTO> inventoryDTO = inventoryService.findByProductId(productId);
        return ResponseUtil.wrapOrNotFound(inventoryDTO);
    }

    /**
     * {@code GET  /inventories/:id} : get the "id" inventory.
     *
     * @param id the id of the inventoryDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the inventoryDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<InventoryDTO> getInventory(@PathVariable("id") String id) {
        LOG.debug("REST request to get Inventory : {}", id);
        Optional<InventoryDTO> inventoryDTO = inventoryService.findOne(id);
        return ResponseUtil.wrapOrNotFound(inventoryDTO);
    }

    /**
     * {@code DELETE  /inventories/:id} : delete the "id" inventory.
     *
     * @param id the id of the inventoryDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInventory(@PathVariable("id") String id) {
        LOG.debug("REST request to delete Inventory : {}", id);
        inventoryService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }

    /**
     * {@code POST  /purchases} : Create a new purchase.
     *
     * @param purchaseRequest the purchaseRequest to create.
     * @param idempotencyKey the Idempotency-Key header.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new purchaseDTO.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/purchases")
    public ResponseEntity<PurchaseDTO> createPurchase(@Valid @RequestBody PurchaseRequestDTO purchaseRequest, @RequestHeader("Idempotency-Key") String idempotencyKey) throws URISyntaxException {
        LOG.debug("REST request to purchase : {}, key: {}", purchaseRequest, idempotencyKey);
        PurchaseDTO result = inventoryService.purchase(purchaseRequest, idempotencyKey);
        return ResponseEntity.created(new URI("/api/purchases/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, "inventoryPurchase", result.getId()))
            .body(result);
    }
}
