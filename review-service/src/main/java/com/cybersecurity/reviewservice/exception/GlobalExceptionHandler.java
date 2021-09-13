package com.cybersecurity.reviewservice.exception;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

/**
 * @author Ramesh.Yaleru on 9/10/2021
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ExceptionResponse> handleAccessDeniedException(AccessDeniedException ex) {
        log.error("AccessDeniedException : ", ex);
        ExceptionResponse error = ExceptionResponse.builder()
                .message(ex.getMessage())
                .statusCode(HttpStatus.UNAUTHORIZED.value())
                .build();
        return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ExceptionResponse> productNotFoundException(ProductNotFoundException ex) {
        log.error("AccessDeniedException : ", ex);
        ExceptionResponse error = ExceptionResponse.builder()
                .message(ex.getMessage())
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .build();
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
        log.info("Validation exception: ", ex);
        List<ErrorMessage> errorMessages = Lists.newArrayList();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            ErrorMessage errorMessage = ErrorMessage.builder()
                    .errorCode(error.getCode())
                    .messageEn(error.getDefaultMessage())
                    .messageAr(error.getDefaultMessage())
                    .build();
            errorMessages.add(errorMessage);
        });
        ExceptionResponse error = ExceptionResponse.builder()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .message("Validation Errors!")
                .errors(errorMessages)
                .build();

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({Exception.class, AuthenticationException.class})
    public ResponseEntity<ExceptionResponse> exception(AuthenticationException e) {

        log.error("Exception : ", e);
        ExceptionResponse errorInfo = ExceptionResponse.builder()
                .message(e.getLocalizedMessage())
                .statusCode(HttpStatus.UNAUTHORIZED.value())
                .build();

        return ResponseEntity.status(UNAUTHORIZED).body(errorInfo);
    }
}
