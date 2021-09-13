package com.cybersecurity.cloudgateway;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Ramesh.Yaleru on 9/11/2021
 */
@RestController
public class HystrixController {
    @GetMapping("/reviewServiceFallBack")
    public String reviewServiceFallBackMethod() {
        return "Review service seems to be Down or some issues.Please try again later";
    }

    @GetMapping("/productServiceFallBack")
    public String productServiceFallBackMethod() {
        return "Product service seems to be Down or some issues.Please try again later";
    }
}
