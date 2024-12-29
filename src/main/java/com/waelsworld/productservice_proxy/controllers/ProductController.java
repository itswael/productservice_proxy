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
        try {
            List<Product> products = this.productService.getAllProducts();
            if(products != null)
                return new ResponseEntity<>(products, HttpStatus.OK);
            else
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
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
            if(product == null){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
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
        try{
            product = this.productService.addNewProduct(product);
            if(product == null){
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
            }
            return new ResponseEntity<>(product, HttpStatus.OK);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PutMapping("/{productId}")
    public ResponseEntity<Product> updateProduct(@PathVariable("productId") long productId, @RequestBody Product product){
        try {
            product = this.productService.updateProduct(product, productId);
            if(product == null){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
            return new ResponseEntity<>(product,HttpStatus.OK);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<String> deleteProduct(@PathVariable("productId") long productId){
        try{
            String message = this.productService.deleteProduct(productId);
            if(message == null){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
            return new ResponseEntity<>(message, HttpStatus.OK);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping( "/categories")
    public ResponseEntity<List<String>> getAllCategories(){
        try{
            List<String> categories = this.productService.getAllCategories();
            if(categories != null)
                return new ResponseEntity<>(this.productService.getAllCategories(), HttpStatus.OK);
            else
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
