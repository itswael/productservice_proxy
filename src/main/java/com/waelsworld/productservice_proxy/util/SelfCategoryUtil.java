package com.waelsworld.productservice_proxy.util;

import com.waelsworld.productservice_proxy.clients.fakestore.dto.SelfCategoryDto;

import java.util.ArrayList;
import java.util.List;

public class SelfCategoryUtil {
    public static List<String> getCategoryNames(List<SelfCategoryDto> categories){
        List<String> categoryNames = new ArrayList<>();
        for (SelfCategoryDto category : categories) {
            categoryNames.add(category.getName());
        }
        return categoryNames;
    }
}
