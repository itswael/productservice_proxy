package com.waelsworld.productservice_proxy.repositories;

import com.waelsworld.productservice_proxy.models.Category;
import com.waelsworld.productservice_proxy.models.Product;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;

@SpringBootTest
public class ProductRepoTest {
    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private CategoryRepo categoryRepo;
    @Test
    @Transactional
    void saveProductsAndCategory() {
        Category category = new Category();
        category.setName("Electronics");
        category.setDescription("Electronics");
        category = categoryRepo.save(category);
        Product product = new Product();
        product.setTitle("Laptop");
        product.setDescription("Laptop");
        product.setCategory(category);
        productRepo.save(product);
        Category category1 = categoryRepo.findById(category.getId()).get();
        List<Product> productList = category1.getProductList();
        System.out.println("Debug");
    }

    @Test
        //@Transactional
    void saveProductsAndCategory2() {
        Category categories = new Category();
        categories.setName("Fashion");
        categories.setDescription("Fashion");
        //categories = categoryRepo.save(categories);
        Product product = new Product();
        product.setTitle("Tshirt");
        product.setDescription("Tshirt");
        product.setCategory(categories);
        productRepo.save(product);
        //Categories categories1 = categoryRepo.findById(categories.getId()).get();
        //List<Product> productList = categories1.getProductList();
        System.out.println("Debug");
    }
    @Test
    @Transactional
    void saveProductsAndCategory1() {
        Product product = new Product();
        product.setTitle("Laptop");
        product.setDescription("Laptop");
        productRepo.save(product);
        Category category = new Category();
        category.setName("Electronics");
        category.setDescription("Electronics");
        category.setProductList(List.of(product));
        category = categoryRepo.save(category);
        Category category1 = categoryRepo.findById(category.getId()).get();
        List<Product> productList = category1.getProductList();
        System.out.println("Debug");
    }

    @Test
    @Transactional
    @Rollback(value = false)
    void saveProductsAndCategory3() {
        Category category = categoryRepo.findById(2L);
        //List<Product> productList = category.getProductList();
//        for (Product product : productList) {
//            System.out.println(product.getPrice());
//        }
        System.out.println("Debug");

//        Product product = new Product();
//        product.setPrice(1012);
//        product.setImageUrl("hiii");
//        product.setCategory(category);
//        Product savedProduct = productRepo.save(product);
//
//        product = new Product();
//        product.setPrice(103);
//        product.setImageUrl("hiii");
//        product.setCategory(category);
//        productRepo.save(product);


    }

    @Test
    @Transactional
        //@Rollback(value = false)
    void saveProductsAndCategory4() {
//        Categories category = categoryRepo.findById(2L);
//        List<Product> productList = category.getProductList();
//        for (Product product : productList) {
//            System.out.println(product.getPrice());
//        }

//        Product product = productRepo.findByPriceBetween(1000, 1012);
        //List<Product> productList = productRepo.findByIdIsNotNullOrderByPrice();
//        String s = productRepo.findTitleById(252L);
        System.out.println("Debug");


//        Product product = new Product();
//        product.setPrice(1012);
//        product.setImageUrl("hiii");
//        product.setCategory(category);
//        Product savedProduct = productRepo.save(product);
//
//        product = new Product();
//        product.setPrice(103);
//        product.setImageUrl("hiii");
//        product.setCategory(category);
//        productRepo.save(product);


    }
}
