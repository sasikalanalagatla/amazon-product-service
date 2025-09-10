package com.amazon.product_service.dto;

import jakarta.validation.constraints.*;
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
public class ProductRequestDto {

    @NotBlank(message = "Product name is required")
    @Size(max = 255, message = "Product name must not exceed 255 characters")
    private String name;

    @Size(max = 1000, message = "Description must not exceed 1000 characters")
    private String description;

    @NotNull(message = "Price is required")
    @DecimalMin(value = "0.01", message = "Price must be greater than 0")
    @Digits(integer = 8, fraction = 2, message = "Price format is invalid")
    private BigDecimal price;

    @NotNull(message = "Stock quantity is required")
    @Min(value = 0, message = "Stock quantity cannot be negative")
    private Integer stockQuantity;

    @Size(max = 100, message = "Brand name must not exceed 100 characters")
    private String brand;

    private Long viewCount = 0L;

    @Size(max = 100, message = "Category must not exceed 100 characters")
    private String category;

    @Size(max = 100)
    private String parentCategory;

    private List<String> imageUrls;

    @DecimalMin(value = "0.0", message = "Rating cannot be negative")
    @DecimalMax(value = "5.0", message = "Rating cannot exceed 5.0")
    private Double rating;

    @Min(value = 0, message = "Review count cannot be negative")
    private Integer reviewCount;

    @Size(max = 50, message = "SKU must not exceed 50 characters")
    private String sku;

    @DecimalMin(value = "0.0", message = "Weight cannot be negative")
    private Double weight;

    @Size(max = 50, message = "Dimensions must not exceed 50 characters")
    private String dimensions;

    private Boolean isActive;
    private Boolean isFeatured;
}
