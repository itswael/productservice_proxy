package com.waelsworld.productservice_proxy.clients.fakestore.client;

import com.waelsworld.productservice_proxy.clients.fakestore.dto.FakeStoreProductDto;
import com.waelsworld.productservice_proxy.models.Category;
import com.waelsworld.productservice_proxy.models.Product;
import com.waelsworld.productservice_proxy.util.FakeStoreProductUtil;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class FakeStoreClient {
    /*
    RestTemplateBuilder helps in getting a json mapped to an object
    reference variable and a constructor is created, instantiation will be done by spring
     */
    RestTemplateBuilder restTemplateBuilder;
    public FakeStoreClient(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplateBuilder = restTemplateBuilder;
    }

    private <T> ResponseEntity<T> requestForEntity(HttpMethod httpMethod, String url, @Nullable Object request,
                                                   Class<T> responseType, Object... uriVariables) throws RestClientException {
        RestTemplate restTemplate = restTemplateBuilder.requestFactory(
                                        HttpComponentsClientHttpRequestFactory.class
                                            ).build();
        RequestCallback requestCallback =restTemplate.httpEntityCallback(request, responseType);
        ResponseExtractor<ResponseEntity<T>> responseExtractor = restTemplate.responseEntityExtractor(responseType);
        return restTemplate.execute(url, httpMethod, requestCallback, responseExtractor, uriVariables);
    }

    /*
    function to get all the products,
    though we are returning the list of products,
    but the restTemplate doesn't support list due to generic type,
    as all the list (integer/string) appears to be same.
    so I have passed ProductDto[] array to the get all the products from the API.
     */
    public List<Product> getAllProducts() {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto[]> responseEntity = restTemplate.getForEntity("https://fakestoreapi.com/products", FakeStoreProductDto[].class);
        List<Product> products = new ArrayList<>();
        for (FakeStoreProductDto fakeStoreProductDto : Objects.requireNonNull(responseEntity.getBody())) {
            products.add(FakeStoreProductUtil.getProduct(fakeStoreProductDto));
        }
        return products;
    }

    public Product getSingleProduct(Long id) {
        RestTemplate restTemplate = restTemplateBuilder.build();

        /*
        restTemplate is fetching the json object from the given third party URL and the id parameter to be used
        is passed as third variable, this json is to be converted and assigned to productDto hence we are providing
        a template, what json is to be converted to.
         */
        FakeStoreProductDto fakeStoreProductDto = restTemplate.getForEntity("https://fakestoreapi.com/products/{id}", FakeStoreProductDto.class, id).getBody();
        assert fakeStoreProductDto != null;
        return FakeStoreProductUtil.getProduct(fakeStoreProductDto);
    }

    public Product addNewProduct(Product product) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        FakeStoreProductDto productDto = FakeStoreProductUtil.getFakeStoreProductDto(product);
        productDto = restTemplate.postForEntity("https://fakestoreapi.com/products", productDto, FakeStoreProductDto.class).getBody();
        assert productDto != null;
        return FakeStoreProductUtil.getProduct(productDto);
    }

    public Product updateProduct(Product product, Long id) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        FakeStoreProductDto productDto = FakeStoreProductUtil.getFakeStoreProductDto(product);
        //productDto = restTemplate.patchForObject("https://fakestoreapi.com/products/{id}", productDto, FakeStoreProductDto.class, id);
        productDto = requestForEntity(HttpMethod.PATCH, "https://fakestoreapi.com/products/{id}", productDto, FakeStoreProductDto.class, id).getBody();
        assert productDto != null;
        return FakeStoreProductUtil.getProduct(productDto);
    }

    public String deleteProduct(Long id) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        restTemplate.delete("https://fakestoreapi.com/products/{id}", id);
        return id.toString();
    }

    public List<String> getAllCategories() {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<String[]> responseEntity = restTemplate.getForEntity("https://fakestoreapi.com/products/categories", String[].class);
        return List.of(Objects.requireNonNull(responseEntity.getBody()));
    }

    public List<Product> getSingleCategory(String categoryName) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto[]> responseEntity  = restTemplate.getForEntity("https://fakestoreapi.com/products/category/{categoryName}", FakeStoreProductDto[].class, categoryName);
        List<Product> products = new ArrayList<>();
        for (FakeStoreProductDto fakeStoreProductDto : Objects.requireNonNull(responseEntity.getBody())) {
            products.add(FakeStoreProductUtil.getProduct(fakeStoreProductDto));
        }
        return products;
    }
}
