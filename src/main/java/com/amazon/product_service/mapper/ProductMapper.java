package com.amazon.product_service.mapper;

import com.amazon.product_service.dto.ProductRequestDto;
import com.amazon.product_service.dto.ProductResponseDto;
import com.amazon.product_service.dto.ProductSummaryDto;
import com.amazon.product_service.model.Product;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductMapper {

    public ProductResponseDto toResponseDto(Product product) {
        if (product == null) {
            return null;
        }

        return ProductResponseDto.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .stockQuantity(product.getStockQuantity())
                .brand(product.getBrand())
                .category(product.getCategory())
                .parentCategory(product.getParentCategory())
                .imageUrls(product.getImageUrls())
                .viewCount(product.getViewCount())
                .rating(product.getRating())
                .reviewCount(product.getReviewCount())
                .sku(product.getSku())
                .weight(product.getWeight())
                .dimensions(product.getDimensions())
                .isActive(product.getIsActive())
                .isFeatured(product.getIsFeatured())
                .createdAt(product.getCreatedAt())
                .updatedAt(product.getUpdatedAt())
                .build();
    }

    public List<ProductResponseDto> toResponseDtos(List<Product> products) {
        if (products == null) {
            return null;
        }

        List<ProductResponseDto> responseDtos = new ArrayList<>();
        for (Product product : products) {
            ProductResponseDto dto = toResponseDto(product);
            if (dto != null) {
                responseDtos.add(dto);
            }
        }
        return responseDtos;
    }

    public ProductSummaryDto toSummaryDto(Product product) {
        if (product == null) {
            return null;
        }

        return ProductSummaryDto.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .brand(product.getBrand())
                .category(product.getCategory())
                .parentCategory(product.getParentCategory())
                .imageUrls(product.getImageUrls())
                .viewCount(product.getViewCount())
                .rating(product.getRating())
                .reviewCount(product.getReviewCount())
                .isActive(product.getIsActive())
                .isFeatured(product.getIsFeatured())
                .build();
    }

    public List<ProductSummaryDto> toSummaryDtos(List<Product> products) {
        if (products == null) {
            return null;
        }

        List<ProductSummaryDto> summaryDtos = new ArrayList<>();
        for (Product product : products) {
            ProductSummaryDto dto = toSummaryDto(product);
            if (dto != null) {
                summaryDtos.add(dto);
            }
        }
        return summaryDtos;
    }

    public static Product toEntity(ProductRequestDto productRequestDto) {
        if (productRequestDto == null) {
            return null;
        }

        return Product.builder()
                .name(productRequestDto.getName())
                .description(productRequestDto.getDescription())
                .price(productRequestDto.getPrice())
                .stockQuantity(productRequestDto.getStockQuantity())
                .brand(productRequestDto.getBrand())
                .viewCount(productRequestDto.getViewCount())
                .category(productRequestDto.getCategory())
                .parentCategory(productRequestDto.getParentCategory())
                .imageUrls(productRequestDto.getImageUrls())
                .rating(productRequestDto.getRating())
                .reviewCount(productRequestDto.getReviewCount())
                .sku(productRequestDto.getSku())
                .weight(productRequestDto.getWeight())
                .dimensions(productRequestDto.getDimensions())
                .isActive(productRequestDto.getIsActive() != null ? productRequestDto.getIsActive() : true)
                .isFeatured(productRequestDto.getIsFeatured() != null ? productRequestDto.getIsFeatured() : false)
                .build();
    }

    public void updateEntityFromDto(ProductRequestDto productRequestDto, Product product) {
        if (productRequestDto == null || product == null) {
            return;
        }

        if (productRequestDto.getName() != null) {
            product.setName(productRequestDto.getName());
        }

        if (productRequestDto.getDescription() != null) {
            product.setDescription(productRequestDto.getDescription());
        }

        if (productRequestDto.getPrice() != null) {
            product.setPrice(productRequestDto.getPrice());
        }

        if (productRequestDto.getStockQuantity() != null) {
            product.setStockQuantity(productRequestDto.getStockQuantity());
        }

        if (productRequestDto.getBrand() != null) {
            product.setBrand(productRequestDto.getBrand());
        }

        if (productRequestDto.getCategory() != null) {
            product.setCategory(productRequestDto.getCategory());
        }

        if (productRequestDto.getParentCategory() != null) {
            product.setParentCategory(productRequestDto.getParentCategory());
        }

        if (productRequestDto.getImageUrls() != null) {
            product.setImageUrls(productRequestDto.getImageUrls());
        }

        if (productRequestDto.getRating() != null) {
            product.setRating(productRequestDto.getRating());
        }

        if (productRequestDto.getReviewCount() != null) {
            product.setReviewCount(productRequestDto.getReviewCount());
        }

        if (productRequestDto.getViewCount() != null){
            product.setViewCount(productRequestDto.getViewCount());
        }

        if (productRequestDto.getSku() != null) {
            product.setSku(productRequestDto.getSku());
        }

        if (productRequestDto.getWeight() != null) {
            product.setWeight(productRequestDto.getWeight());
        }

        if (productRequestDto.getDimensions() != null) {
            product.setDimensions(productRequestDto.getDimensions());
        }

        if (productRequestDto.getIsActive() != null) {
            product.setIsActive(productRequestDto.getIsActive());
        }

        if (productRequestDto.getIsFeatured() != null) {
            product.setIsFeatured(productRequestDto.getIsFeatured());
        }
    }

    // Helper method for creating new entity with ID (useful for updates)
    public Product toEntityWithId(Long id, ProductRequestDto productRequestDto) {
        if (productRequestDto == null) {
            return null;
        }

        Product product = toEntity(productRequestDto);
        if (product != null) {
            product.setId(id);
        }
        return product;
    }

    // Utility method for copying image URLs safely
    public List<String> copyImageUrls(List<String> originalUrls) {
        if (originalUrls == null) {
            return null;
        }

        List<String> copiedUrls = new ArrayList<>();
        for (String url : originalUrls) {
            if (url != null && !url.trim().isEmpty()) {
                copiedUrls.add(url);
            }
        }
        return copiedUrls;
    }
}
