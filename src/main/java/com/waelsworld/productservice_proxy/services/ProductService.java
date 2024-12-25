package com.waelsworld.productservice_proxy.services;

import com.waelsworld.productservice_proxy.dtos.ProductDto;
import com.waelsworld.productservice_proxy.models.Product;
import com.waelsworld.productservice_proxy.util.ProductUtil;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.MultiValueMapAdapter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService implements IProductService {
    /*
    RestTemplateBuilder helps in getting a json mapped to an object
    reference variable and a constructor is created, instantiation will be done by spring
     */
    private final RestTemplateBuilder restTemplateBuilder;
    public ProductService(RestTemplateBuilder restTemplateBuilder){
        this.restTemplateBuilder = restTemplateBuilder;
    }
    /*
    function to get all the products,
    though we are returning the list of products,
    but the restTemplate doesnt support list due to generic type,
    as all the list (integer/string) appears to be same.
    so I have passed ProductDto[] array to the get all the products from the API.
     */
    @Override
    public List<Product> getAllProducts() {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<ProductDto[]> responseEntity = restTemplate.getForEntity("https://fakestoreapi.com/products", ProductDto[].class);
        List<Product> products = new ArrayList<>();
        for (ProductDto productDto : responseEntity.getBody()) {
            products.add(ProductUtil.getProduct(productDto));
        }
        return products;
    }

    @Override
    public Product getSingleProduct(Long id) {
        RestTemplate restTemplate = restTemplateBuilder.build();

        /*
        restTemplate is fetching the json object from the given third party URL and the id parameter to be used
        is passed as third variable, this json is to be converted and assigned to productDto hence we are providing
        a template, what json is to be converted to.
         */
        ProductDto productDto = restTemplate.getForEntity("https://fakestoreapi.com/products/{id}", ProductDto.class, id).getBody();
        return ProductUtil.getProduct(productDto);
    }


    @Override
    public Product addNewProduct(ProductDto productDto) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        productDto = restTemplate.postForEntity("https://fakestoreapi.com/products", productDto, ProductDto.class).getBody();
        return ProductUtil.getProduct(productDto);
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
