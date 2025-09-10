package com.amazon.product_service.service;

import com.amazon.product_service.dto.ProductRequestDto;
import com.amazon.product_service.dto.ProductResponseDto;
import com.amazon.product_service.dto.ProductSummaryDto;
import com.amazon.product_service.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {
    ProductResponseDto createProduct(ProductRequestDto product);

    ProductResponseDto getProductById(Long id);

    List<ProductResponseDto> getAllProducts();

    ProductResponseDto updateProduct(ProductRequestDto productRequest, Long id);

    void deleteProduct(Long id);

    Page<ProductResponseDto> getProductsByCategory(String categoryName, int page, int size);

    ProductResponseDto updateStock(Long id, Integer quantity);

    Page<ProductResponseDto> getFeaturedProducts(int page, int size);

    Page<ProductResponseDto> getProductsByPriceRange(BigDecimal min, BigDecimal max, int page, int size);

    ProductResponseDto setProductActiveStatus(Long id, boolean b);

    Page<ProductResponseDto> getProductsByBrand(String brand, int page, int size);

    Page<ProductSummaryDto> getAllProductsSorted(int page, int size, String sort);

    Page<ProductResponseDto> getLowStockProducts(Integer threshold, int page, int size);

    Page<ProductResponseDto> searchProducts(String keyword, String category, String brand, BigDecimal minPrice,
                                            BigDecimal maxPrice, int page, int size);

    Page<ProductResponseDto> getProductsByParentCategory(String categoryName, int page, int size);

    Page<Product> getProductsByCategories(List<String> categories, Pageable pageable);

    int bulkUpdateStatus(List<Long> productIds, Boolean isActive);

    void addImages(Long id, List<String> newImages);

    void removeImages(Long id, List<String> imagesToRemove);

    void reorderImages(Long id, List<String> reorderedImages);

    boolean checkSkuExists(String sku);

    ProductResponseDto recordView(Long id);

    List<ProductResponseDto> getPopularProducts(int limit);
}
