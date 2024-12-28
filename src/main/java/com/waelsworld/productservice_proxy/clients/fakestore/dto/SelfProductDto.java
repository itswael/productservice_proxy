package com.waelsworld.productservice_proxy.clients.fakestore.dto;

import com.waelsworld.productservice_proxy.models.Category;
import jakarta.persistence.CascadeType;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
public class SelfProductDto implements IClientProductDto {
    private Long id;
    private Date createdAt;
    private Date lastUpdatedAt;
    private Boolean isDeleted;
    private String title;
    private double price;
    private String description;
    private Category category;
    private String imageUrl;

    public SelfProductDto(Long id, Date createdAt, Boolean isDeleted, Date lastUpdatedAt, String description, String imageUrl, double price, String title, Long categoryId, String categoryName) {
        this.id = id;
        this.createdAt = createdAt;
        this.lastUpdatedAt = lastUpdatedAt;
        this.isDeleted = isDeleted;
        this.title = title;
        this.price = price;
        this.description = description;
        Category category = new Category();
        category.setId(categoryId);
        category.setName(categoryName);
        this.category = category;
        this.imageUrl = imageUrl;
    }
}
