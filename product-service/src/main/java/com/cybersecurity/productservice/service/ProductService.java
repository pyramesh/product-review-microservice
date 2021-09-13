package com.cybersecurity.productservice.service;

import com.cybersecurity.productservice.aggregator.ProductAggregator;
import com.cybersecurity.productservice.dto.ProductDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Ramesh.Yaleru on 9/10/2021
 */
@Service
@Slf4j
public class ProductService {

    @Autowired
    private ProductAggregator productAggregator;

    public String searchProduct(String productId){
        return productAggregator.aggregateSearchResults(productId);
    }
}
