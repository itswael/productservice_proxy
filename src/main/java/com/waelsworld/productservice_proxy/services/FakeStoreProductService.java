package com.waelsworld.productservice_proxy.services;

import com.waelsworld.productservice_proxy.clients.fakestore.client.FakeStoreClient;
import com.waelsworld.productservice_proxy.models.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FakeStoreProductService implements IProductService {

    private final FakeStoreClient fakeStoreClient;
    public FakeStoreProductService(FakeStoreClient fakeStoreClient) {
        this.fakeStoreClient = fakeStoreClient;
    }

    @Override
    public List<Product> getAllProducts() {
        return fakeStoreClient.getAllProducts();
    }

    @Override
    public Product getSingleProduct(Long id) {
        return fakeStoreClient.getSingleProduct(id);
    }

    @Override
    public Product addNewProduct(Product product) {
        return fakeStoreClient.addNewProduct(product);
    }

    @Override
    public Product updateProduct(Product product, Long id) {
        return fakeStoreClient.updateProduct(product, id);
    }

    @Override
    public String deleteProduct(Long id) {
        return fakeStoreClient.deleteProduct(id);
    }


}
