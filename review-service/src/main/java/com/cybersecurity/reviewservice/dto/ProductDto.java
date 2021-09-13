package com.cybersecurity.reviewservice.dto;

import com.cybersecurity.reviewservice.validator.NotNullField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author Ramesh.Yaleru on 9/10/2021
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@NotNullField.List({
        @NotNullField(fieldName = "averageReviewScore", message = "average review score must not be null or empty."),
        @NotNullField(fieldName = "numberOfReviews", message = "number of reviews must not be null or empty."),


})
public class ProductDto implements Serializable {

    private String productId;
    private double averageReviewScore;
    private int numberOfReviews;
}

