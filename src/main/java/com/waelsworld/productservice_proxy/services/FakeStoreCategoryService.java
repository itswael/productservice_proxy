package com.waelsworld.productservice_proxy.services;

import com.waelsworld.productservice_proxy.clients.fakestore.client.FakeStoreClient;
import com.waelsworld.productservice_proxy.models.Category;
import com.waelsworld.productservice_proxy.models.Product;
import org.springframework.stereotype.Service;

import java.util.List;
//@Service
public class FakeStoreCategoryService implements ICategoryService{
    FakeStoreClient fakeStoreClient;
    public FakeStoreCategoryService(FakeStoreClient fakeStoreClient) {
        this.fakeStoreClient = fakeStoreClient;
    }

    @Override
    public List<Product> getSingleCategory(String CategoryName) {
        return fakeStoreClient.getSingleCategory(CategoryName);
    }
}
