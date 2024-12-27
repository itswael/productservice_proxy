package com.waelsworld.productservice_proxy.services;

import com.waelsworld.productservice_proxy.clients.fakestore.client.FakeStoreClient;
import com.waelsworld.productservice_proxy.clients.fakestore.dto.FakeStoreProductDto;
import com.waelsworld.productservice_proxy.models.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
@SpringBootTest
public class FakeStoreProductServiceTest {
    @Autowired
    private FakeStoreClient fakeStoreClient;
    @Test
    public void Test_FakeStoreClient() {
        List<Product> result =  fakeStoreClient.getAllProducts();
        assertNotNull(result);
    }
}
