package com.waelsworld.productservice_proxy.controllers;

import com.waelsworld.productservice_proxy.models.Product;
import com.waelsworld.productservice_proxy.services.IProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<List<Product>> getAllProducts(){
        return new ResponseEntity<>(this.productService.getAllProducts(),HttpStatus.OK);
    }
    /*
    getting a string object returned from the service and returning to the caller.
     */
    @GetMapping("/{productId}")
    public ResponseEntity<Product> getSingleProduct(@PathVariable("productId") Long productId){
        try {
            MultiValueMap<String,String> headers = new LinkedMultiValueMap<>();
            headers.add("Accept", "application/json");
            headers.add("Content-Type", "application/json");
            Product product = this.productService.getSingleProduct(productId);
            return new ResponseEntity<>(product,headers, HttpStatus.OK);
        }catch (Exception e){
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    /*
    -> updated the return type to ResponseEntity<product>.
    Response entity contains, data + httpStatus + headers.
    -> To add headers, we use MultiValueMap<String, String> <- LinkedMultiValueMap<>()
     */

    @PostMapping()
    public ResponseEntity<Product> addNewProduct(@RequestBody Product product){
        product = this.productService.addNewProduct(product);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @PutMapping("/{productId}")
    public ResponseEntity<Product> updateProduct(@PathVariable("productId") long productId, @RequestBody Product product){
        product = this.productService.updateProduct(product, productId);
        return new ResponseEntity<>(product,HttpStatus.OK);
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<String> deleteProduct(@PathVariable("productId") long productId){
        return new ResponseEntity<>(this.productService.deleteProduct(productId), HttpStatus.OK);
    }

    @GetMapping( "/categories")
    public ResponseEntity<List<String>> getAllCategories(){
        List<String> categories = productService.getAllCategories();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }
}
