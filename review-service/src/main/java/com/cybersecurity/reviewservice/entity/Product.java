package com.cybersecurity.reviewservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Ramesh.Yaleru on 9/10/2021
 */
@Entity
@Table(name = "PRODUCT")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product implements Serializable {

    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "PRODUCT_ID")
    private String productId;

    @Column(name = "AVERAGE_REVIEW_SCORE")
    private double averageReviewScore;

    @Column(name = "NUMBER_OF_REVIEWS")
    private int numberOfReviews;


}
