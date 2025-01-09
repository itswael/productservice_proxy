package com.waelsworld.productservice_proxy.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.elasticsearch.annotations.Document;

@Getter
@Setter
@Entity
@Document(indexName = "productservice")
public class Product extends BaseModel{
    private String title;
    private double price;
    private String description;
    @ManyToOne(cascade= CascadeType.ALL)
    private Category category;
    private String imageUrl;

}
