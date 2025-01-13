package com.waelsworld.productservice_proxy.services.sortingService;

import com.waelsworld.productservice_proxy.models.Product;

import java.util.List;

public interface Sorter {
    List<Product> sort(List<Product> products);
}
