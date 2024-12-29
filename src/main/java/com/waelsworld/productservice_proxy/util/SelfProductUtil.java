package com.waelsworld.productservice_proxy.util;

import com.waelsworld.productservice_proxy.clients.fakestore.dto.SelfProductDto;
import com.waelsworld.productservice_proxy.models.Product;

import java.util.ArrayList;
import java.util.List;

public class SelfProductUtil {

    public static List<Product> getProducts(List<SelfProductDto> selfProductDtos){
        List<Product> products = new ArrayList<>();
        for(SelfProductDto selfProductDto : selfProductDtos){
            Product product = new Product();
            product.setImageUrl(selfProductDto.getImageUrl());
            product.setId(selfProductDto.getId());
            product.setCategory(selfProductDto.getCategory());
            product.setTitle(selfProductDto.getTitle());
            product.setDescription(selfProductDto.getDescription());
            product.setPrice(selfProductDto.getPrice());
            product.setCreatedAt(selfProductDto.getCreatedAt());
            product.setIsDeleted(selfProductDto.getIsDeleted());
            product.setLastUpdatedAt(selfProductDto.getLastUpdatedAt());
            products.add(product);
        }
        return products;
    }

    public static Product getUpdatedProduct(Product existingProduct, Product updatedProduct){
        if (updatedProduct.getTitle() != null) {
            existingProduct.setTitle(updatedProduct.getTitle());
        }
        if (updatedProduct.getDescription() != null) {
            existingProduct.setDescription(updatedProduct.getDescription());
        }
        if (updatedProduct.getPrice() >= 0) {
            existingProduct.setPrice(updatedProduct.getPrice());
        }
        if (updatedProduct.getImageUrl() != null) {
            existingProduct.setImageUrl(updatedProduct.getImageUrl());
        }
        if (updatedProduct.getCategory() != null) {
            existingProduct.setCategory(updatedProduct.getCategory());
        }
        return existingProduct;
    }
}
