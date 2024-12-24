package com.waelsworld.productservice_proxy.services;

import com.waelsworld.productservice_proxy.dtos.ProductDto;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ProductService implements IProductService {
    /*
    RestTemplateBuilder helps in getting a json mapped to an object
    reference variable and a constructor is created, instantiation will be done by spring
     */
    private RestTemplateBuilder restTemplateBuilder;
    public ProductService(RestTemplateBuilder restTemplateBuilder){
        this.restTemplateBuilder = restTemplateBuilder;
    }
    @Override
    public String getAllProducts() {
        return "All Products";
    }

    @Override
    public String getSingleProduct(Long id) {
        RestTemplate restTemplate = restTemplateBuilder.build();

        /*
        restTemplate is fetching the json object from the given third party URL and the id parameter to be used
        is passed as third variable, this json is to be converted and assigned to productDto hence we are providing
        a template, what json is to be converted to.
         */
        ProductDto productDto = restTemplate.getForEntity("https://fakestoreapi.com/products/{id}", ProductDto.class, id).getBody();
        return productDto.toString();
    }

    @Override
    public String addNewProduct(ProductDto productDto) {
        return "New product added: " + productDto;
    }

    @Override
    public String updateProduct(Long id) {
        return "Product updated with id: " + id;
    }

    @Override
    public String deleteProduct(Long id) {
        return "Product deleted with id: " + id;
    }
}
