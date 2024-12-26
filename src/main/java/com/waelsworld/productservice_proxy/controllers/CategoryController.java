package com.waelsworld.productservice_proxy.controllers;

import com.waelsworld.productservice_proxy.models.Category;
import com.waelsworld.productservice_proxy.models.Product;
import com.waelsworld.productservice_proxy.services.FakeStoreCategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products/category")
public class CategoryController {
    FakeStoreCategoryService fakeStoreCategoryService;
    public CategoryController(FakeStoreCategoryService fakeStoreCategoryService) {
        this.fakeStoreCategoryService = fakeStoreCategoryService;
    }

    @GetMapping("/{categoryName}")
    public ResponseEntity<List<Product>> getProductsInCategory(@PathVariable("categoryName") String categoryName){
        List<Product> products = fakeStoreCategoryService.getSingleCategory(categoryName);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
}
