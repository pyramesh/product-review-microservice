package com.cybersecurity.productservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author Ramesh.Yaleru on 9/10/2021
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto implements Serializable {
    private String productId;
    private double averageReviewScore;
    private int numberOfReviews;
}

