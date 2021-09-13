package com.cybersecurity.reviewservice.controller;

import com.cybersecurity.reviewservice.dto.ProductDto;
import com.cybersecurity.reviewservice.entity.Product;
import com.cybersecurity.reviewservice.service.ProductReviewService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Base64;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Ramesh.Yaleru on 9/12/2021
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
@Slf4j
@AutoConfigureMockMvc
public class ProductReviewControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductReviewService productReviewService;

    HttpHeaders headers = new HttpHeaders();

    @BeforeEach
    public void init() {
        // create auth credentials
        String authStr = "client:client";
        String base64Creds = Base64.getEncoder().encodeToString(authStr.getBytes());
        headers.add("Authorization", "Basic " + base64Creds);

    }

    @Test
    public void creatProductReview_SUCCESS() {
        ProductDto productDto = new ProductDto();
        productDto.setProductId("REST343");
        productDto.setAverageReviewScore(100.00);
        productDto.setNumberOfReviews(100);

        // create request
        HttpEntity request = new HttpEntity(productDto, headers);

        ResponseEntity<Product> responseEntity = restTemplate.exchange("/v1/review", HttpMethod.POST, request, Product.class);
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    }

    @Test
    public void creatProductReview_UNAUTHORIZED() {
        ProductDto productDto = new ProductDto();
        productDto.setProductId("REST3431");
        productDto.setAverageReviewScore(100.00);
        productDto.setNumberOfReviews(100);

        // create request
        HttpEntity request = new HttpEntity(productDto);

        ResponseEntity<Product> responseEntity = restTemplate.exchange("/v1/review", HttpMethod.POST, request, Product.class);
        assertEquals(HttpStatus.UNAUTHORIZED, responseEntity.getStatusCode());
    }
    @Test
    public void creatProductReview_SUCCESS_1() {
        ProductDto productDto = new ProductDto();
        productDto.setProductId("QBCD123");
        productDto.setAverageReviewScore(100.00);
        productDto.setNumberOfReviews(100);

        // create request
        HttpEntity request = new HttpEntity(productDto, headers);

        ResponseEntity<Product> responseEntity = restTemplate.exchange("/v1/review", HttpMethod.POST, request, Product.class);
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    }

    //@Test
    public void getReview_SUCCESS() {
        // create request
        HttpEntity request = new HttpEntity(headers);
        ResponseEntity<ProductDto> responseEntity = restTemplate.exchange("/v1/review/QBCD123", HttpMethod.GET, request, ProductDto.class);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void getReview_NOTFOUND() {
        // create request
        HttpEntity request = new HttpEntity(headers);
        ResponseEntity<ProductDto> responseEntity = restTemplate.exchange("/v1/review/REST000", HttpMethod.GET, request, ProductDto.class);
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    public void getAllReview_SUCCESS() {
        // create request
        HttpEntity request = new HttpEntity(headers);
        ResponseEntity<List> responseEntity = restTemplate.exchange("/v1/review", HttpMethod.GET, request, List.class);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void getDeleteReview_NOTFound() throws Exception {
        String authStr = "admin:admin";
        String base64Creds = Base64.getEncoder().encodeToString(authStr.getBytes());
        // create request
        doNothing().when(productReviewService).deleteReview("REST343");
        mockMvc.perform(delete("/v1/review/REST343")
                .contentType(MediaType.APPLICATION_JSON)
        .header("Authorization", "Basic " + base64Creds))
                .andExpect(status().isNotFound());
    }
    @Test
    public void getDeleteReview_UNAUTHORIZED() throws Exception {
        // create request
        doNothing().when(productReviewService).deleteReview("REST343");
        mockMvc.perform(delete("/v1/review/REST343")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized());
    }

}
