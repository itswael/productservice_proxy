package com.waelsworld.productservice_proxy.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product extends BaseModel{
    private String title;
    private double price;
    private String description;
    private String categories;
    private String imageUrl;

}
