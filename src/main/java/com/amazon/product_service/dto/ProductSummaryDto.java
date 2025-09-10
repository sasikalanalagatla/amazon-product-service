package com.amazon.product_service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductSummaryDto {

    private Long id;
    private String name;
    private BigDecimal price;
    private String brand;
    private String category;
    private Long viewCount;
    private String parentCategory;
    private List<String> imageUrls;
    private Double rating;
    private Integer reviewCount;
    private Boolean isActive;
    private Boolean isFeatured;
}
