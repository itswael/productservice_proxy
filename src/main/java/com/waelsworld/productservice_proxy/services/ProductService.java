package com.waelsworld.productservice_proxy.services;

import com.waelsworld.productservice_proxy.dtos.ProductDto;

public class ProductService implements IProductService {
    @Override
    public String getAllProducts() {
        return "All Products";
    }

    @Override
    public String getSingleProduct(Long id) {
        return "Product with id: " + id;
    }

    @Override
    public String addNewProduct(ProductDto productDto) {
        return "New product added: " + productDto;
    }

    @Override
    public String updateProduct(Long id) {
        return "Product updated with id: " + id;
    }

    @Override
    public String deleteProduct(Long id) {
        return "Product deleted with id: " + id;
    }
}
