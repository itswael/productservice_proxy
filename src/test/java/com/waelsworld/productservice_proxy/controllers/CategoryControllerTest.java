package com.waelsworld.productservice_proxy.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.waelsworld.productservice_proxy.models.Product;
import com.waelsworld.productservice_proxy.services.SelfCategoryService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = CategoryController.class)
class CategoryControllerTest {
    @MockBean
    SelfCategoryService selfCategoryService;

    @Autowired
    MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("Test when products available then return list of products")
    void testProductsAvailable() throws Exception {
        List<Product> products = new ArrayList<>();
        when(selfCategoryService.getSingleCategory(any(String.class))).thenReturn(products);

        mockMvc.perform(get("/products/category/abc"))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(products)));
    }

    @Test
    @DisplayName("Test when products unavailable then return notfound")
    void testProductsUnAvailable() throws Exception {
        List<Product> products = null;
        when(selfCategoryService.getSingleCategory(any(String.class))).thenReturn(products);

        mockMvc.perform(get("/products/category/abc"))
                .andExpect(status().isNotFound());
    }
}