package com.waelsworld.productservice_proxy.dtos;

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
    private String categories;
    private String imageUrl;
    private RatingDto rating;
}
