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
}
