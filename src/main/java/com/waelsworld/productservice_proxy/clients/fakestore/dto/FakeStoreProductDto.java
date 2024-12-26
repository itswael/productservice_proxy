package com.waelsworld.productservice_proxy.clients.fakestore.dto;

import com.waelsworld.productservice_proxy.dtos.RatingDto;
import com.waelsworld.productservice_proxy.models.Category;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class FakeStoreProductDto implements IClientProductDto {
    private Long id;
    private String title;
    private double price;
    private String description;
    private String category;
    private String image;
    private RatingDto rating;
}
