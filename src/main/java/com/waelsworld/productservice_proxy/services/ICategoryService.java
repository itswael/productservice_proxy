package com.waelsworld.productservice_proxy.services;

import com.waelsworld.productservice_proxy.models.Category;
import com.waelsworld.productservice_proxy.models.Product;

import java.util.List;

public interface ICategoryService {
    List<Product> getSingleCategory(String CategoryName);
}
