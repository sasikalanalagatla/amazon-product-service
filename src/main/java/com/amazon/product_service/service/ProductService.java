package com.amazon.product_service.service;

import com.amazon.product_service.dto.ProductRequestDto;
import com.amazon.product_service.dto.ProductResponseDto;
import com.amazon.product_service.dto.ProductSummaryDto;
import com.amazon.product_service.model.Product;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {
    ProductResponseDto createProduct(ProductRequestDto product);

    ProductResponseDto getProductById(Long id);

    List<ProductResponseDto> getAllProducts();

    ProductResponseDto updateProduct(ProductRequestDto productRequest, Long id);

    void deleteProduct(Long id);

    List<ProductResponseDto> getProductsByCategory(String categoryName);

    ProductResponseDto updateStock(Long id, Integer quantity);

    List<ProductResponseDto> getFeaturedProducts();

    List<ProductResponseDto> getProductsByPriceRange(BigDecimal min, BigDecimal max);

    ProductResponseDto setProductActiveStatus(Long id, boolean b);

    List<ProductResponseDto> getProductsByBrand(String brand);

    List<ProductSummaryDto> getAllProductsSorted(String sort);

    List<ProductResponseDto> getLowStockProducts(Integer threshold);

    List<ProductResponseDto> searchProducts(String keyword, String category, String brand, BigDecimal minPrice, BigDecimal maxPrice);

    List<ProductResponseDto> getProductsByParentCategory(String categoryName);

    List<Product> getProductsByCategories(List<String> categories);

    int bulkUpdateStatus(List<Long> productIds, Boolean isActive);

    void addImages(Long id, List<String> newImages);

    void removeImages(Long id, List<String> imagesToRemove);

    void reorderImages(Long id, List<String> reorderedImages);

    boolean checkSkuExists(String sku);

    ProductResponseDto recordView(Long id);

    List<ProductResponseDto> getPopularProducts(int limit);
}
