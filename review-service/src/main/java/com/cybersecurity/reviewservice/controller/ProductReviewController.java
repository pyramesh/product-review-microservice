package com.cybersecurity.reviewservice.controller;

import com.cybersecurity.reviewservice.dto.ProductDto;
import com.cybersecurity.reviewservice.entity.Product;
import com.cybersecurity.reviewservice.exception.ProductNotFoundException;
import com.cybersecurity.reviewservice.service.ProductReviewService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Ramesh.Yaleru on 9/10/2021
 */
@RestController
@RequestMapping("/v1")
public class ProductReviewController {

    ProductReviewService productReviewService;

    ProductReviewController(ProductReviewService productReviewService) {
        this.productReviewService = productReviewService;
    }

    @PreAuthorize("hasRole('CLIENT')")
    @PostMapping("/review")
    public ResponseEntity saveReview(@RequestHeader HttpHeaders headers, @RequestBody ProductDto request) {
        Product product = productReviewService.saveReview(request);
        return new ResponseEntity(product, HttpStatus.CREATED);
    }

    @GetMapping("/review/{product_id}")
    public ResponseEntity getReview(@PathVariable("product_id") String productId) throws ProductNotFoundException {
        ProductDto product = productReviewService.getReview(productId);
        if(product != null){
            return new ResponseEntity(product, HttpStatus.OK);
        }
        return new ResponseEntity(product, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/review")
    public ResponseEntity getReviews() {
        List<Product> products = null;
        products = productReviewService.getAllReviews();
        return new ResponseEntity(products, HttpStatus.OK);

    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/review/{product_id}")
    public ResponseEntity deleteReview(@PathVariable("product_id") String productId) {

        if (productReviewService.getReview(productId) == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        productReviewService.deleteReview(productId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);

    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/review")
    public ResponseEntity deleteAllReview() {
        productReviewService.deleteAll();
        return new ResponseEntity(HttpStatus.NO_CONTENT);

    }

    @PreAuthorize("hasRole('CLIENT')")
    @PutMapping("/review/{product_id}")
    public ResponseEntity deleteAllReview(@PathVariable("product_id") String productId, @RequestBody ProductDto productDto) {
        if (productId != null) {
            productReviewService.updateReview(productId, productDto);
        }
        return new ResponseEntity(HttpStatus.NO_CONTENT);

    }
}
