package com.waelsworld.productservice_proxy.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class FilterDto {
    private String key;
    private List<String> values;
}
