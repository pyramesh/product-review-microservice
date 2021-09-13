package com.cybersecurity.reviewservice.dto;

import com.cybersecurity.reviewservice.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * @author Ramesh.Yaleru on 9/10/2021
 */
@Data
@AllArgsConstructor
public class UserDto {

    int id;
    String userName;
    boolean isActive;
    List<Role> roles;
}
