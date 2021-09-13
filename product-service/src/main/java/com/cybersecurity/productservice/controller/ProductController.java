package com.cybersecurity.productservice.controller;

import com.cybersecurity.productservice.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author Ramesh.Yaleru on 9/10/2021
 */
@RestController
@RequestMapping("/v1")
@Slf4j
public class ProductController {

    private ProductService productService;

    ProductController(ProductService productService) {
        this.productService = productService;
    }

    @Autowired
    RestTemplate restTemplate;

    @GetMapping(value = "/product/{product_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getProducts(@PathVariable("product_id") String productId) {
        String response = productService.searchProduct(productId);
        if(response != null){
            return new ResponseEntity(response, HttpStatus.OK);
        }
        return new ResponseEntity(response, HttpStatus.NOT_FOUND);

    }
}
