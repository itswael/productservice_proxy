package com.waelsworld.productservice_proxy.services;

import com.waelsworld.productservice_proxy.dtos.ProductDto;

public interface IProductService {
    String getAllProducts();

    String getSingleProduct(Long id);

    String addNewProduct(ProductDto productDto);

    String updateProduct(Long id);

    String deleteProduct(Long id);
}
