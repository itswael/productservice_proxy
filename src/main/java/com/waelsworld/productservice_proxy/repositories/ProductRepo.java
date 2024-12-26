package com.waelsworld.productservice_proxy.repositories;

import com.waelsworld.productservice_proxy.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepo extends JpaRepository<Product, Long> {
    Product save(Product product);
}
