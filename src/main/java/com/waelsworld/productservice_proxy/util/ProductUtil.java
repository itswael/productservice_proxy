package com.waelsworld.productservice_proxy.util;

import com.waelsworld.productservice_proxy.dtos.ProductDto;
import com.waelsworld.productservice_proxy.models.Product;

public class ProductUtil {

    /*
    static getProduct function to translate productDto to product. utilized by service after retrieving productDto from API.
     */
    public static Product getProduct(ProductDto productDto) {
        Product product = new Product();
        product.setId(productDto.getId());
        product.setTitle(productDto.getTitle());
        product.setPrice(productDto.getPrice());
        product.setCategories(productDto.getCategory());
        product.setDescription(productDto.getDescription());
        product.setImageUrl(productDto.getImage());
        return product;
    }
}
