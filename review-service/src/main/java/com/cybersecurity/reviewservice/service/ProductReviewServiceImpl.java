package com.cybersecurity.reviewservice.service;

import com.cybersecurity.reviewservice.dto.ProductDto;
import com.cybersecurity.reviewservice.entity.Product;
import com.cybersecurity.reviewservice.mapper.ReviewMapper;
import com.cybersecurity.reviewservice.repository.ProductReviewRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Ramesh.Yaleru on 9/10/2021
 */
@Service
@Transactional
public class ProductReviewServiceImpl implements ProductReviewService{

    private ReviewMapper reviewMapper = Mappers.getMapper(ReviewMapper.class);;

    private ProductReviewRepository reviewRepository;

    ProductReviewServiceImpl(ProductReviewRepository reviewRepository){
        this.reviewRepository =reviewRepository;
    }

    @Transactional
    @Override
    public Product saveReview(ProductDto productDto) {
        Product product = reviewMapper.map(productDto);
        return reviewRepository.save(product);
    }

    @Override
    public ProductDto getReview(String productId) {
        Product productDb=  reviewRepository.findById(productId).orElse(null);
        return reviewMapper.map(productDb);
    }

    @Override
    public List<Product> getAllReviews() {
        return reviewRepository.findAll();
    }

    @Override
    public void updateReview(String productId, ProductDto productDto) {
        Product product = reviewRepository.findById(productId).orElse(null);
        if(product != null){
            product.setAverageReviewScore(productDto.getAverageReviewScore());
            product.setNumberOfReviews(productDto.getNumberOfReviews());
            reviewRepository.save(product);
        }

    }

    @Override
    public void deleteReview(String productId) {
        reviewRepository.deleteById(productId);
    }

    @Override
    public void deleteAll() {
        reviewRepository.deleteAll();
    }
}
