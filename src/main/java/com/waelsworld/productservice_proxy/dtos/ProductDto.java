package com.waelsworld.productservice_proxy.dtos;

import com.waelsworld.productservice_proxy.models.Category;
import com.waelsworld.productservice_proxy.models.Product;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString()
public class ProductDto {
    private Long id;
    private String title;
    private double price;
    private String description;
    private String category;
    private String image;
    private RatingDto rating;

    public static ProductDto from(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setTitle(product.getTitle());
        productDto.setPrice(product.getPrice());
        productDto.setDescription(product.getDescription());
        productDto.setCategory(product.getCategory().getName());
        productDto.setImage(product.getImageUrl());
        return productDto;
    }
}
