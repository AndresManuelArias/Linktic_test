package com.tienda.inventory.client;


import java.util.UUID;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "products", url = "http://localhost:8081")
public interface ProductsClient {

    @GetMapping("/api/products/{id}")
    ResponseEntity<Void> getProduct(@PathVariable("id") UUID id);
}