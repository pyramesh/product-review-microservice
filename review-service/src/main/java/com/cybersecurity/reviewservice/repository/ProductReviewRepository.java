package com.cybersecurity.reviewservice.repository;

import com.cybersecurity.reviewservice.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Ramesh.Yaleru on 9/10/2021
 */
@Repository
public interface ProductReviewRepository extends JpaRepository<Product, String> {
}
