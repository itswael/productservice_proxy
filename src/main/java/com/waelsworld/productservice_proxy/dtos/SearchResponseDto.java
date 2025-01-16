package com.waelsworld.productservice_proxy.dtos;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

@Getter
@Setter
public class SearchResponseDto {
    private Page<ProductDto> productsPage;
}
