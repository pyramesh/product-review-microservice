package com.cybersecurity.reviewservice.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author Ramesh.Yaleru on 9/10/2021
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorMessage implements Serializable {
    private String errorCode;
    private String messageEn;
    private String messageAr;

}
