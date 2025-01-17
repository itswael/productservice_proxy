package com.waelsworld.productservice_proxy.repositories;

import com.waelsworld.productservice_proxy.clients.fakestore.dto.SelfCategoryDto;
import com.waelsworld.productservice_proxy.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepo extends JpaRepository<Product, Long> {
    Product save(Product product);

    String deleteProductById(Long id);

    @Query(value = "SELECT new " +
            "com.waelsworld.productservice_proxy.clients.fakestore.dto.SelfCategoryDto" +
            "(c.id, c.createdAt, c.isDeleted, c.lastUpdatedAt, c.description, c.name) " +
            "from Category c")
    List<SelfCategoryDto> findAllCategories();

    //Page<Product> findByTitleEquals(String title, Pageable pageable);
    List<Product> findByTitleEquals(String title, Pageable pageable);

    List<Product> findByTitleContaining(String query);
    Page<Product> findAllByTitleContainingAndCategory_Id(
            String title, Long categoryId, Pageable pageable
    );

}
