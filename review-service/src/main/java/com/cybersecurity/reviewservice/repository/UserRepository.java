package com.cybersecurity.reviewservice.repository;

import com.cybersecurity.reviewservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Ramesh.Yaleru on 9/10/2021
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUserName(String userName);
}
