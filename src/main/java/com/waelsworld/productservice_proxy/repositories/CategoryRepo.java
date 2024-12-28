package com.waelsworld.productservice_proxy.repositories;

import com.waelsworld.productservice_proxy.clients.fakestore.dto.SelfProductDto;
import com.waelsworld.productservice_proxy.clients.fakestore.dto.SelfCategoryDto;
import com.waelsworld.productservice_proxy.models.Category;
import com.waelsworld.productservice_proxy.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CategoryRepo extends JpaRepository<Category, Long> {
    @Query(value = "SELECT new com.waelsworld.productservice_proxy.clients.fakestore.dto.SelfProductDto( " +
            "p.id, p.createdAt, p.isDeleted, p.lastUpdatedAt, p.description, p.imageUrl, p.price, p.title, " +
            "p.category.id, p.category.name)" +
            "FROM Product p " +
            "WHERE p.category.name = :CategoryName")
    List<SelfProductDto> getProductsByCategoryName(@Param("CategoryName") String CategoryName);
    Category save(Category category);
    Category findByName(String name);
    Category findById(long id);

    @Query(value = "SELECT c.name FROM Category c WHERE c.id = :id")
    String findCategoryNameById(@Param("id") long id);
    @Query(value = "SELECT c.name FROM Category c WHERE c.id = ?1")
    String findCategoryNameByIdV1(long id);
}
