package com.waelsworld.productservice_proxy.controllers;

import com.waelsworld.productservice_proxy.models.Product;
import com.waelsworld.productservice_proxy.services.ICategoryService;
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
    ICategoryService categoryService;
    public CategoryController(ICategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/{categoryName}")
    public ResponseEntity<List<Product>> getProductsInCategory(@PathVariable("categoryName") String categoryName){
        try{
            List<Product> products = categoryService.getSingleCategory(categoryName);
            if(products != null)
                return new ResponseEntity<>(products, HttpStatus.OK);
            else
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

    }
}
