package com.amazon.product_service.repository;

import com.amazon.product_service.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Page<Product> findByCategoryIgnoreCase(String category, Pageable pageable);
    Page<Product> findByIsFeaturedTrue(Pageable pageable);
    Page<Product> findByPriceBetween(BigDecimal min, BigDecimal max, Pageable pageable);
    Page<Product> findByBrandIgnoreCase(String brand, Pageable pageable);
    Page<Product> findByStockQuantityLessThan(Integer threshold, Pageable pageable);
    Page<Product> findByParentCategoryIgnoreCase(String category, Pageable pageable);

    @Query("SELECT p FROM Product p " +
            "WHERE (:keyword IS NULL OR LOWER(p.name) LIKE %:keyword% OR LOWER(p.description) LIKE %:keyword%) " +
            "AND (:category IS NULL OR LOWER(p.parentCategory) = LOWER(:category)) " +
            "AND (:brand IS NULL OR LOWER(p.brand) = LOWER(:brand)) " +
            "AND (:minPrice IS NULL OR p.price >= :minPrice) " +
            "AND (:maxPrice IS NULL OR p.price <= :maxPrice)")
    Page<Product> searchProducts(
            @Param("keyword") String keyword,
            @Param("category") String category,
            @Param("brand") String brand,
            @Param("minPrice") BigDecimal minPrice,
            @Param("maxPrice") BigDecimal maxPrice,
            Pageable pageable
    );

    Page<Product> findByParentCategoryInIgnoreCase(List<String> categories, Pageable pageable);

    boolean existsBySku(String sku);
}
