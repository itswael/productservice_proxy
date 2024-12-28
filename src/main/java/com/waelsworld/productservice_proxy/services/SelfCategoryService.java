package com.waelsworld.productservice_proxy.services;

import com.waelsworld.productservice_proxy.util.SelfProductUtil;
import com.waelsworld.productservice_proxy.clients.fakestore.dto.SelfProductDto;
import com.waelsworld.productservice_proxy.models.Product;
import com.waelsworld.productservice_proxy.repositories.CategoryRepo;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SelfCategoryService implements ICategoryService {
    CategoryRepo categoryRepo;
    public SelfCategoryService(CategoryRepo categoryRepo) {
        this.categoryRepo = categoryRepo;
    }
    @Override
    public List<Product> getSingleCategory(String CategoryName) {
        List<SelfProductDto> selfProductDtos = this.categoryRepo.getProductsByCategoryName(CategoryName);
        return SelfProductUtil.getProducts(selfProductDtos);
    }
}
