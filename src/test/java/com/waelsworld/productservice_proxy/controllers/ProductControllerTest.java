package com.waelsworld.productservice_proxy.controllers;

import com.waelsworld.productservice_proxy.repositories.ProductRepo;
import com.waelsworld.productservice_proxy.services.SelfProductService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.waelsworld.productservice_proxy.models.Product;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class ProductControllerTest {

    //  Flow:
    // (responseEntity) productController (clientParameters) <-> (requestedObject) ProductService (Parameters)
    // (requestedObject) ProductService (Parameters) <-> (requestedObject) ProductRepo (Query) <-> DB
    // Since we are testing Product controller, others would be mock annotated by @MockBean
    // but the product controller is initialized by @Autowired
    @MockBean
    SelfProductService selfProductService;
    @MockBean
    ProductRepo productRepo;
    @Autowired
    ProductController productController;

    @Test
    @DisplayName("Test when getAllProducts then return list of product")
    void test_whenGetAllProducts_thenReturnListOfProduct() {
        //Arrangement
        List<Product> products = new ArrayList<>();
        when(selfProductService.getAllProducts()).thenReturn(products);

        //Act
        ResponseEntity<List<Product>> response = productController.getAllProducts();

        //Assert
        // this will ensure, we did not receive null
        assertNotNull(productController.getAllProducts());
        // this will validate if the status code is correct
        assertEquals(HttpStatus.OK,response.getStatusCode());
        // this will validate if we receive the List of products
        assertEquals(products,response.getBody());
    }

    @Test
    @DisplayName("Test when getAllProducts then return null")
    void test_whenGetAllProducts_thenReturnNull() {
        //Arrangement
        List<Product> products = null;
        when(selfProductService.getAllProducts()).thenReturn(null);

        //Act
        ResponseEntity<List<Product>> response = productController.getAllProducts();

        //Assert
        // this will validate if the status code is correct
        assertEquals(HttpStatus.NOT_FOUND,response.getStatusCode());
        // this will validate if we receive the List of products
        assertEquals(products,response.getBody());
    }

    @Test
    void getSingleProduct() {
    }

    @Test
    void addNewProduct() {
    }

    @Test
    void updateProduct() {
    }

    @Test
    void deleteProduct() {
    }

    @Test
    void getAllCategories() {
    }
}