package com.waelsworld.productservice_proxy.services;

import com.waelsworld.productservice_proxy.clients.fakestore.dto.SelfCategoryDto;
import com.waelsworld.productservice_proxy.models.Product;
import com.waelsworld.productservice_proxy.repositories.ProductElasticSearchRepo;
import com.waelsworld.productservice_proxy.repositories.ProductRepo;
import com.waelsworld.productservice_proxy.util.SelfCategoryUtil;
import com.waelsworld.productservice_proxy.util.SelfProductUtil;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SelfProductService implements IProductService {
    ProductRepo productRepo;
    ProductElasticSearchRepo productElasticSearchRepo;

    public SelfProductService(ProductRepo productRepo, ProductElasticSearchRepo productElasticSearchRepo) {
        this.productRepo = productRepo;
        this.productElasticSearchRepo = productElasticSearchRepo;
    }
    @Override
    public List<Product> getAllProducts() {
        return this.productRepo.findAll();
    }
    @Override
    public Product getSingleProduct(Long productId) {
        return this.productRepo.findById(productId).orElse(null);
    }
    @Override
    public Product addNewProduct(Product product) {
        this.productRepo.save(product);
        this.productElasticSearchRepo.save(product);
        return product;
    }

    @Override
    public Product updateProduct(Product product, Long id) {
        Optional<Product> optionalProduct = productRepo.findById(id);

        if (optionalProduct.isEmpty()) {
            throw new IllegalArgumentException("Product with ID " + id + " not found.");
        }

        // Save method updates the product if id already exist
        return productRepo.save(SelfProductUtil.getUpdatedProduct(optionalProduct.get(), product));
    }

    @Override
    public String deleteProduct(Long productId) {
        return this.productRepo.deleteProductById(productId);
    }

    @Override
    public List<String> getAllCategories() {
        List<SelfCategoryDto> categories = this.productRepo.findAllCategories();
        if(categories != null) {
            return SelfCategoryUtil.getCategoryNames(categories);
        }
        return null;
    }
}
