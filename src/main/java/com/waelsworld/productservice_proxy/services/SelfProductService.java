package com.waelsworld.productservice_proxy.services;

import com.waelsworld.productservice_proxy.models.Product;
import com.waelsworld.productservice_proxy.repositories.ProductRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SelfProductService implements IProductService {
    ProductRepo productRepo;
    public SelfProductService(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }
    @Override
    public List<Product> getAllProducts() {
        return null;
    }
    @Override
    public Product getSingleProduct(Long productId) {
        return null;
    }
    @Override
    public Product addNewProduct(Product product) {
        this.productRepo.save(product);
        return product;
    }

    @Override
    public Product updateProduct(Product product, Long id) {
        return null;
    }

    @Override
    public String deleteProduct(Long productId) {
        return null;
    }

    @Override
    public List<String> getAllCategories() {
        return List.of();
    }
}
