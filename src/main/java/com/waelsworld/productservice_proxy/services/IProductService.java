package com.waelsworld.productservice_proxy.services;

import com.waelsworld.productservice_proxy.models.Product;

import java.util.List;

public interface IProductService {
    List<Product> getAllProducts();

    Product getSingleProduct(Long id);

    Product addNewProduct(Product product);

    Product updateProduct(Product product, Long id);

    String deleteProduct(Long id);
}
