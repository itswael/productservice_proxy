package com.waelsworld.productservice_proxy.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.waelsworld.productservice_proxy.services.SelfProductService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import com.waelsworld.productservice_proxy.models.Product;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
class ProductControllerTest {
    // Testing Best Practices
    // 1. Arrange, Act, Assert
    // 2. Each test should run in isolation of others
    // 3. Repeatable (No Flakiness)
    // 4. self Checking
    // 5. Test the behaviour, not implementation

    //  Flow:
    // (responseEntity) productController (clientParameters) <-> (requestedObject) ProductService (Parameters)
    // (requestedObject) ProductService (Parameters) <-> (requestedObject) ProductRepo (Query) <-> DB
    // Since we are testing Product controller, others would be mock annotated by @MockBean
    // but the product controller is initialized by @Autowired

    @MockBean
    SelfProductService selfProductService;
//    @MockBean
//    ProductRepo productRepo;
//    @Autowired
//    ProductController productController;

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("Test when getAllProducts then return list of product")
    void test_whenGetAllProducts_thenReturnListOfProduct() throws Exception {
        //Arrangement
        List<Product> products = new ArrayList<>();
        when(selfProductService.getAllProducts()).thenReturn(products);

        mockMvc.perform(get("/products")) //Act
                .andExpect(status().isOk()) //Assert
                        .andExpect(content().string(objectMapper.writeValueAsString(products)));

    }

    @Test
    @DisplayName("Test when getAllProducts then return null")
    void test_whenGetAllProducts_thenReturnNull() throws Exception {
        //Arrangement
        List<Product> products = null;
        when(selfProductService.getAllProducts()).thenReturn(products);

        mockMvc.perform(get("/products"))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("Test when getSingleProduct then return product")
    void test_when_getSingleProduct_then_returnProduct() throws Exception{
        Product product = new Product();
        when(selfProductService.getSingleProduct(1L)).thenReturn(product);
            mockMvc.perform(get("/products/1"))
                    .andExpect(status().isOk())
                        .andExpect(content().string(objectMapper.writeValueAsString(product)));
    }

    @Test
    @DisplayName("Test when getSingleProduct then return null")
    void test_when_getSingleProduct_then_returnNull() throws Exception{
        Product product = null;
        when(selfProductService.getSingleProduct(1L)).thenReturn(product);
        ResponseEntity<Product> responseEntity = new ResponseEntity<>(product, HttpStatus.OK);
        mockMvc.perform(get("/products/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("Test when addNewProduct then return added Product")
    void when_addNewProduct_then_return_added_Product() throws Exception{
        Product product = new Product();
        when(selfProductService.addNewProduct(any(Product.class))).thenReturn(product);
        mockMvc.perform(post("/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(product)))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(product)));
    }

    @Test
    @DisplayName("Test when addNewProduct null then return internal server error")
    void when_addNewProduct_null_then_return_error() throws Exception{
        Product product = null;
        when(selfProductService.addNewProduct(any(Product.class))).thenReturn(product);
        mockMvc.perform(post("/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(product)))
                .andExpect(status().isInternalServerError());
    }

    @Test
    @DisplayName("Test when updated a product and return product")
    void Test_when_updated_a_product_and_return_product() throws Exception{
        Product product = new Product();
        Long id = 1L;
        when(selfProductService.updateProduct(any(Product.class), any(Long.class))).thenReturn(product);
        mockMvc.perform(put("/products/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(product)))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(product)));
    }

    @Test
    @DisplayName("Test when updated product not found and return 404")
    void Test_when_updated_product_notFound_and_return_404() throws Exception{
        Product product = new Product();
        Long id = 1L;
        when(selfProductService.updateProduct(any(Product.class), any(Long.class))).thenReturn(null);
        mockMvc.perform(put("/products/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(product)))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("Test when deleting a valid product")
    void test_when_deleting_a_valid_product() throws Exception {
        Long id = 1L;
        when(selfProductService.deleteProduct(any(Long.class))).thenReturn("deleted");

        mockMvc.perform(delete("/products/1"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Test when deleting a invalid product")
    void test_when_deleting_a_invalid_product() throws Exception {
        Long id = 1L;
        when(selfProductService.deleteProduct(any(Long.class))).thenReturn(null);

        mockMvc.perform(delete("/products/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("Test When categories available")
    void test_when_categories_available() throws Exception {
        List<String> categories = new ArrayList<>();
        when(selfProductService.getAllCategories()).thenReturn(categories);
        mockMvc.perform(get("/products/categories"))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(categories)));
    }

    @Test
    @DisplayName("Test When categories unavailable")
    void test_when_categories_unavailable() throws Exception {
        List<String> categories = null;
        when(selfProductService.getAllCategories()).thenReturn(categories);
        mockMvc.perform(get("/products/categories"))
                .andExpect(status().isNotFound());
    }
}