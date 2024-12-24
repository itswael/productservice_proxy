package com.waelsworld.productservice_proxy.controllers;

import com.waelsworld.productservice_proxy.dtos.ProductDto;
import org.springframework.web.bind.annotation.*;

//this controller will always answer products
@RestController
@RequestMapping("/products")
public class ProductController {
    @GetMapping("")
    public String getAllProducts(){
        return "all products";
    }

    @GetMapping("/{productId}")
    public String getSingleProduct(@PathVariable("productId") Long productId){
        return "single product with id: "+productId;
    }

    @PostMapping()
    public String addNewProduct(@RequestBody ProductDto productDto){
        return "new product added " + productDto;
    }

    @PutMapping("/{productId}")
    public String updateProduct(@PathVariable("productId") long productId){
        return "product updated with id: "+productId;
    }

    @DeleteMapping("/{productId}")
    public String deleteProduct(@PathVariable("productId") long productId){
        return "product deleted with id: "+productId;
    }
}
