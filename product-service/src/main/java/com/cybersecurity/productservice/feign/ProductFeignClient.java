package com.cybersecurity.productservice.feign;

import com.cybersecurity.productservice.dto.ProductDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Ramesh.Yaleru on 9/10/2021
 */
@FeignClient(value = "review-service", url = "${review.service.api.url}",
        configuration = ProductFeignConfiguration.class)
public interface ProductFeignClient {

    @RequestMapping(method = RequestMethod.GET, value = "/v1/review/{product_id}",
            produces = "application/json")
    ProductDto searchProduct(@PathVariable("product_id") String productId);
}
