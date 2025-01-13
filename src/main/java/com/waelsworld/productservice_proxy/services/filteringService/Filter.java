package com.waelsworld.productservice_proxy.services.filteringService;

import com.waelsworld.productservice_proxy.models.Product;

import java.util.List;
public interface Filter {
    List<Product> apply(List<Product> products,
                        List<String> allowedValues);
}