package com.waelsworld.productservice_proxy.services;

import com.waelsworld.productservice_proxy.dtos.FilterDto;
import com.waelsworld.productservice_proxy.models.Product;
import com.waelsworld.productservice_proxy.models.SortParam;
import com.waelsworld.productservice_proxy.repositories.ProductRepo;
import com.waelsworld.productservice_proxy.services.filteringService.FilterFactory;
import com.waelsworld.productservice_proxy.services.sortingService.SorterFactory;
import com.waelsworld.productservice_proxy.services.sortingService.SortingCriteria;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class SearchService {
    private ProductRepo productRepo;
    public SearchService(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }
//    public Page<Product> searchProducts(String query, int pageNumber, int sizeOfPage) {
//        return productRepo.findByTitleEquals(query, PageRequest.of(pageNumber, sizeOfPage));
//    }

    public List<Product> searchProducts(String query, int pageNumber, int sizeOfPage,
                                        List<SortParam> sortParamList) {
//        Sort sort = Sort.by("title").descending()
//                .and(Sort.by("price").descending());
        Sort sort;
        if(sortParamList.get(0).getSortType().equals("ASC")) {
            sort = Sort.by(sortParamList.get(0).getParamName());
        } else {
            sort = Sort.by(sortParamList.get(0).getParamName()).descending();
        }
        for(int i = 1; i< sortParamList.size(); i++) {
            if(sortParamList.get(i).getSortType().equals("ASC")) {
                sort = sort.and(Sort.by(sortParamList.get(i).getParamName()));
            } else {
                sort = sort.and(Sort.by(sortParamList.get(i).getParamName())
                        .descending());
            }
        }
        return productRepo.findByTitleEquals(query, PageRequest.of(pageNumber, sizeOfPage, sort));
    }

    public Page<Product> search(
            String query,
            List<FilterDto> filters,
            SortingCriteria sortingCriteria,
            int pageNumber, // 1    // 2
            int pageSize    // 5    // 5 -> (5 * (pageNumber - 1)) -> (pageNumber * pageSize) - 1
            //                           [5 -> 9]
    ) {
        List<Product> products = productRepo
                .findByTitleContaining(query);
        for (FilterDto filterDto: filters) {
            products = FilterFactory.getFilterFromKey(
                    filterDto.getKey()
            ).apply(products, filterDto.getValues());
        }
        products = SorterFactory.getSorterByCriteria(sortingCriteria).sort(products);
        List<Product> productsOnPage = new ArrayList<>();
        for (int i = pageSize * (pageNumber - 1); i <= (pageSize * pageNumber) - 1; ++i) {
            productsOnPage.add(products.get(i));
        }
        Pageable pageable = PageRequest.of(pageNumber, pageSize); // Adjust page number and size as needed
        return new PageImpl<>(productsOnPage, pageable, products.size());
    }
    public Page<Product> simpleSearch(
            String query,
            Long categoryId,
            int pageNumber,
            int pageSize,
            String sortingAttribute
    ) {
        // select * from products
        // where title like "%query%"
        // and categoryID = {categoryId}
        // limit {pageSize} offset pageNumber * pageSize
        // orderBy sortingAttribute;
        Page<Product> products = productRepo
                .findAllByTitleContainingAndCategory_Id(
                        query, categoryId,
                        PageRequest.of(
                                pageNumber,
                                pageSize,
                                Sort.by(sortingAttribute).descending()));
        return products;
    }
}
