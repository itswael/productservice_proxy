package com.waelsworld.productservice_proxy.clients.fakestore.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class SelfCategoryDto {
    private Long id;
    private Date createdAt;
    private Date lastUpdatedAt;
    private Boolean isDeleted;
    private String name;
    private String description;

    public SelfCategoryDto(Long id, Date createdAt, Boolean isDeleted, Date lastUpdatedAt, String description, String name) {
        this.id = id;
        this.createdAt = createdAt;
        this.lastUpdatedAt = lastUpdatedAt;
        this.isDeleted = isDeleted;
        this.name = name;
        this.description = description;
    }
}
