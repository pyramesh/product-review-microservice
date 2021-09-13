package com.cybersecurity.reviewservice.mapper;

import com.cybersecurity.reviewservice.dto.ProductDto;
import com.cybersecurity.reviewservice.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.ReportingPolicy;

import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

/**
 * @author Ramesh.Yaleru on 9/10/2021
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, nullValuePropertyMappingStrategy = IGNORE, nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface ReviewMapper {
    Product map(ProductDto productDto);

    ProductDto map(Product product);
}
