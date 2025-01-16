package com.waelsworld.productservice_proxy.controllers;

import com.waelsworld.productservice_proxy.dtos.FilterDto;
import com.waelsworld.productservice_proxy.dtos.ProductDto;
import com.waelsworld.productservice_proxy.dtos.SearchRequestDto;
import com.waelsworld.productservice_proxy.dtos.SearchResponseDto;
import com.waelsworld.productservice_proxy.models.Product;
import com.waelsworld.productservice_proxy.services.SearchService;
import com.waelsworld.productservice_proxy.services.sortingService.SortingCriteria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/search")
public class SearchController {
    SearchService searchService;
    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @PostMapping
    public List<ProductDto> searchProducts(@RequestBody SearchRequestDto searchRequestDto) {
        List<Product> result = searchService.searchProducts(searchRequestDto.getQuery(),
                searchRequestDto.getPageNumber(), searchRequestDto.getSizeOfPage(), searchRequestDto.getSortParamList());
        List<ProductDto> shareableResult = new LinkedList<>();
        for(Product product : result) {
            shareableResult.add(getProduct(product));
        }
        return shareableResult;
    }
//    @PostMapping
//    public Page<Product> searchProducts(@RequestBody SearchRequestDto searchRequestDto) {
////        List<Product> result = searchService.searchProducts(searchRequestDto.getQuery(),
////                searchRequestDto.getPageNumber(), searchRequestDto.getSizeOfPage());
////        List<ProductDto> shareableResult = new LinkedList<>();
////        for(Product product : result) {
////            shareableResult.add(getProduct(product));
////        }
////        return shareableResult;
//        Page<Product> result = searchService.searchProducts(searchRequestDto.getQuery(),
//                searchRequestDto.getPageNumber(), searchRequestDto.getSizeOfPage());
//        return result;
//    }

@GetMapping("/")
public SearchResponseDto search(@RequestParam("query") String query,
                                @RequestParam("filters")List<FilterDto> filters,
                                @RequestParam("sortBy") SortingCriteria sortingCriteria,
                                @RequestParam("pageNumber") int pageNumber,
                                @RequestParam("pageSize") int pageSize
){
    SearchResponseDto response = new SearchResponseDto();
    Page<Product> productsPage =  searchService.search(
            query, filters, sortingCriteria, pageNumber, pageSize);
    List<ProductDto> getProductDtos = productsPage.getContent().stream()
            .map(ProductDto::from)
            .collect(Collectors.toList());
    Pageable pageable = PageRequest.of(productsPage.getNumber(), productsPage.getSize(), productsPage.getSort());
    Page<ProductDto> getProductDtoPage = new PageImpl<>(getProductDtos, pageable, productsPage.getTotalElements());
    response.setProductsPage(getProductDtoPage);
    return response;
}
    @GetMapping("/byCategory")
    public SearchResponseDto simpleSearch(@RequestParam("query") String query,
                                          @RequestParam("category") Long categoryId,
                                          @RequestParam("pageNumber") int pageNumber,
                                          @RequestParam("pageSize") int pageSize,
                                          @RequestParam("sortingAttribute") String sortingAttribute
    ) {
    }

    private ProductDto getProduct(Product p) {
        ProductDto product = new ProductDto();
        product.setId(p.getId());
        product.setTitle(p.getTitle());
        product.setPrice(p.getPrice());
        return product;
    }
}
