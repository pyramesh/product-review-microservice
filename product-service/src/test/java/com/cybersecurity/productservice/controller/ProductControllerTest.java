package com.cybersecurity.productservice.controller;

import com.cybersecurity.productservice.dto.ProductDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

/**
 * @author Ramesh.Yaleru on 9/13/2021
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
@Slf4j
@AutoConfigureMockMvc
public class ProductControllerTest {
    @Autowired
    private TestRestTemplate restTemplate;


    @Test
    public void getProducts_SUCCESS() {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity request = new HttpEntity(headers);
        // create request
        ResponseEntity<String> responseEntity = restTemplate.exchange("/v1/product/BB5476", HttpMethod.GET, request, String.class);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void getProducts_NOTFOUND() {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity request = new HttpEntity(headers);
        // create request
        ResponseEntity<String> responseEntity = restTemplate.exchange("/v1/product/AS231211", HttpMethod.GET, request, String.class);
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }
}
