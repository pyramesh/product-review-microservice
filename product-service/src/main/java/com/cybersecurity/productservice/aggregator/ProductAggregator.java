package com.cybersecurity.productservice.aggregator;

import com.cybersecurity.productservice.feign.AdidasFeignClient;
import com.cybersecurity.productservice.feign.ProductFeignClient;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * @author Ramesh.Yaleru on 9/10/2021
 */
@Component
@Slf4j
public class ProductAggregator {

    @Autowired
    private ProductFeignClient productFeignClient;

    @Autowired
    private AdidasFeignClient adidasFeignClient;

    ObjectMapper mapper = new ObjectMapper();

    @Value("${review.service.api.url}")
    private String reviewServiceBaseUrl;

    @Autowired
    RestTemplate restTemplate;

    public String aggregateSearchResults(String productId){
        JsonNode reviewServiceResponse = null;
        JsonNode addisServiceResponse = null;
        try{
            reviewServiceResponse =restTemplate.getForObject(reviewServiceBaseUrl+productId,JsonNode.class);
        }catch (Exception ecp){
            ecp.printStackTrace();
            reviewServiceResponse= null;
        }
        log.info("reviewServiceResponse = {}", reviewServiceResponse);
        try{
            addisServiceResponse = adidasFeignClient.searchProduct(productId).getBody();
        }catch (FeignException e){
            e.printStackTrace();
            if(e.status() == HttpStatus.NOT_FOUND.value()) {
                addisServiceResponse = null;
            }else{
                throw  e;
            }

        }
        JsonNode finalResponse = mergeCollection(reviewServiceResponse, addisServiceResponse);
        log.info("finalResponse = {}", finalResponse);
        return finalResponse != null ? finalResponse.toString() : null;
    }

    public JsonNode mergeCollection(JsonNode value1,JsonNode value2){
        ObjectNode objectNode = mapper.createObjectNode();
        if(value1!=null) {
            ObjectNode node1 = mapper.createObjectNode();
            node1.set("review_service_res",value1);
            node1.fields().forEachRemaining(kv -> objectNode.set(kv.getKey(), kv.getValue()));
        }
        if(value2!=null) {
            ObjectNode node2 = mapper.createObjectNode();
            node2.set("adidas_service_res",value2);
            node2.fields().forEachRemaining(kv -> objectNode.set(kv.getKey(), kv.getValue()));
        }
        return objectNode.equals(mapper.createObjectNode()) ? null : objectNode;
    }
}
