package com.cybersecurity.reviewservice.service;

import com.cybersecurity.reviewservice.dto.ProductDto;
import com.cybersecurity.reviewservice.entity.Product;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

/**
 * @author Ramesh.Yaleru on 9/13/2021
 */
@Slf4j
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class ProductReviewServiceTest {
    @Autowired
    private ProductReviewService productReviewService;


    @Test
    public void testSaveReview_SUCCESS(){
        ProductDto productDto = new ProductDto();
        productDto.setProductId("AERV1343");
        productDto.setAverageReviewScore(100.00);
        productDto.setNumberOfReviews(10);
        Product response = productReviewService.saveReview(productDto);
        assertEquals(response.getProductId(),productDto.getProductId());


    }

    @Test
    public void testGetReview_SUCCESS(){
        String input = "AERV1343";
        ProductDto response = productReviewService.getReview(input);
        assertEquals(input,response.getProductId());


    }

    @Test
    public void testGetAllReview_SUCCESS(){
        List<Product> response = productReviewService.getAllReviews();
        assertNotNull(response);
    }
    @Test
    public void testUpdateReview_SUCCESS(){
        String inputProductId= "UYTR1343";
        ProductDto productDto = new ProductDto();
        productDto.setProductId(inputProductId);
        productDto.setAverageReviewScore(1010.00);
        productDto.setNumberOfReviews(10000);
        Product response = productReviewService.saveReview(productDto);

        ProductDto updateProductDto = new ProductDto();
        updateProductDto.setNumberOfReviews(20000);
        productReviewService.updateReview(inputProductId, updateProductDto);

        ProductDto productDtoFromDb = productReviewService.getReview(inputProductId);
        assertEquals(20000,productDtoFromDb.getNumberOfReviews());


    }

    @Test
    public void testDeleteReview_SUCCESS(){
        String inputProductId= "RTYY1343";
        ProductDto productDto = new ProductDto();
        productDto.setProductId(inputProductId);
        productDto.setAverageReviewScore(200.00);
        productDto.setNumberOfReviews(200);
        //save
        Product response = productReviewService.saveReview(productDto);

        //delete
        productReviewService.deleteReview(inputProductId);

        //get
        ProductDto productDtoFromDB = productReviewService.getReview(inputProductId);
        assertNull(productDtoFromDB);


    }

}
