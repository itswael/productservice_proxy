package com.waelsworld.productservice_proxy.services.filteringService;

import com.waelsworld.productservice_proxy.models.Product;

import java.util.List;

public class RamFilter implements Filter {
    @Override
    public List<Product> apply(List<Product> products, List<String> allowedValues) {
        return List.of();
    }
}
