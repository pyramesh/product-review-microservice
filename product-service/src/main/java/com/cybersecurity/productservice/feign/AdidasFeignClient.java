package com.cybersecurity.productservice.feign;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Ramesh.Yaleru on 9/10/2021
 */
@FeignClient(value = "adidas-service", url = "${adidas.service.api.url}", configuration = AdidasServiceFeignConfiguration.class)
public interface AdidasFeignClient {
    @RequestMapping(method = RequestMethod.GET, value = "/api/products/{product_id}",
            produces = "application/json")
    ResponseEntity<JsonNode> searchProduct(@PathVariable("product_id") String productId);
}
