package com.cybersecurity.reviewservice.service;

import com.cybersecurity.reviewservice.dto.ProductDto;
import com.cybersecurity.reviewservice.entity.Product;

import java.util.List;

/**
 * @author Ramesh.Yaleru on 9/10/2021
 */
public interface ProductReviewService {

    Product saveReview(ProductDto productDto);
    ProductDto getReview(String productId);
    List<Product> getAllReviews();
    void updateReview(String productId, ProductDto productDto);
    void deleteReview(String productId);
    void deleteAll();





}
