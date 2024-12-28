package com.waelsworld.productservice_proxy.services;

import com.waelsworld.productservice_proxy.clients.fakestore.dto.SelfCategoryDto;
import com.waelsworld.productservice_proxy.models.Product;
import com.waelsworld.productservice_proxy.repositories.ProductRepo;
import com.waelsworld.productservice_proxy.util.SelfCategoryUtil;
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
        return this.productRepo.findAll();
    }
    @Override
    public Product getSingleProduct(Long productId) {
        return this.productRepo.findById(productId).orElse(null);
    }
    @Override
    public Product addNewProduct(Product product) {
        this.productRepo.save(product);
        return product;
    }

    @Override
    public Product updateProduct(Product product, Long id) {
        product = null;//this.productRepo.updateProductById(product, id);
        return product;
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
