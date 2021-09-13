package com.cybersecurity.reviewservice.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Ramesh.Yaleru on 9/10/2021
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductNotFoundException extends RuntimeException {
    private HttpStatus httpStatus;
    private String message;
}
