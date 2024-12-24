package com.waelsworld.productservice_proxy.controllers;

import com.waelsworld.productservice_proxy.dtos.ProductDto;
import com.waelsworld.productservice_proxy.services.IProductService;
import com.waelsworld.productservice_proxy.services.ProductService;
import org.springframework.web.bind.annotation.*;

//this controller will always answer products
@RestController
@RequestMapping("/products")
public class ProductController {

    /*
    -> controller, needs to call service to get the data,
    thus controller using constructor to assign object to service reference variable
    so it can be instantiated via spring.
     */
    IProductService productService;
    public ProductController(IProductService productService){
        this.productService = productService;
    }

    @GetMapping("")
    public String getAllProducts(){
        return "all products";
    }
    /*
    getting a string object returned from the service and returning to the caller.
     */
    @GetMapping("/{productId}")
    public String getSingleProduct(@PathVariable("productId") Long productId){
        String product = productService.getSingleProduct(productId);
        return "single product with id: "+product;
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
