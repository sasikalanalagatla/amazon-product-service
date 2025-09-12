package com.amazon.product_service.service.impl;

import com.amazon.product_service.dto.ProductRequestDto;
import com.amazon.product_service.dto.ProductResponseDto;
import com.amazon.product_service.dto.ProductSummaryDto;
import com.amazon.product_service.exception.ProductNotFoundException;
import com.amazon.product_service.mapper.ProductMapper;
import com.amazon.product_service.model.Product;
import com.amazon.product_service.repository.ProductRepository;
import com.amazon.product_service.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductMapper productMapper;

    @Override
    public ProductResponseDto createProduct(ProductRequestDto productRequest) {
        Product product = productMapper.toEntity(productRequest);
        Product savedProduct = productRepository.save(product);
        return productMapper.toResponseDto(savedProduct);
    }

    @Override
    public ProductResponseDto getProductById(Long id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isEmpty()) {
            throw new ProductNotFoundException("Product not found with id: " + id);
        }
        return productMapper.toResponseDto(product.get());
    }

    @Override
    public List<ProductResponseDto> getAllProducts() {
        List<Product> products = productRepository.findAll();

        if (products.isEmpty()) {
            throw new ProductNotFoundException("No products found");
        }

        return products.stream()
                .map(productMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProductResponseDto updateProduct(ProductRequestDto requestDto, Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id " + id));

        product.setName(requestDto.getName());
        product.setDescription(requestDto.getDescription());
        product.setPrice(requestDto.getPrice());
        product.setStockQuantity(requestDto.getStockQuantity());
        product.setBrand(requestDto.getBrand());
        product.setCategory(requestDto.getCategory());
        product.setImageUrls(requestDto.getImageUrls());
        product.setRating(requestDto.getRating());
        product.setReviewCount(requestDto.getReviewCount());
        product.setSku(requestDto.getSku());
        product.setWeight(requestDto.getWeight());
        product.setDimensions(requestDto.getDimensions());
        product.setIsActive(requestDto.getIsActive());
        product.setIsFeatured(requestDto.getIsFeatured());

        Product updatedProduct = productRepository.save(product);
        return productMapper.toResponseDto(updatedProduct);
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public List<ProductResponseDto> getProductsByCategory(String category) {
        List<Product> products = productRepository.findByCategoryIgnoreCase(category);

        if (products.isEmpty()) {
            throw new ProductNotFoundException("No products found in category: " + category);
        }

        return products.stream()
                .map(productMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductResponseDto> getProductsByParentCategory(String category) {
        List<Product> products = productRepository.findByParentCategoryIgnoreCase(category);

        if (products.isEmpty()) {
            throw new ProductNotFoundException("No products found in parent category: " + category);
        }

        return products.stream()
                .map(productMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductResponseDto> getProductsByBrand(String brand) {
        List<Product> products = productRepository.findByBrandIgnoreCase(brand);

        if (products.isEmpty()) {
            throw new ProductNotFoundException("No products found in brand: " + brand);
        }

        return products.stream()
                .map(productMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProductResponseDto updateStock(Long productId, Integer quantity) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id " + productId));

        product.setStockQuantity(quantity);
        Product savedProduct = productRepository.save(product);
        return productMapper.toResponseDto(savedProduct);
    }

    @Override
    public List<ProductResponseDto> getFeaturedProducts() {
        List<Product> featuredProducts = productRepository.findByIsFeaturedTrue();

        if (featuredProducts.isEmpty()){
            throw new ProductNotFoundException("No featured products found");
        }

        return featuredProducts.stream()
                .map(productMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductResponseDto> getLowStockProducts(Integer threshold) {
        List<Product> lowStockProducts = productRepository.findByStockQuantityLessThan(threshold);

        if (lowStockProducts.isEmpty()) {
            throw new ProductNotFoundException("No products found with stock less than " + threshold);
        }

        return lowStockProducts.stream()
                .map(productMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductResponseDto> searchProducts(
            String keyword,
            String category,
            String brand,
            BigDecimal minPrice,
            BigDecimal maxPrice) {

        List<Product> products = productRepository.searchProducts(
                keyword == null ? null : keyword.toLowerCase(),
                category,
                brand,
                minPrice,
                maxPrice
        );

        return products.stream()
                .map(productMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductResponseDto> getProductsByPriceRange(BigDecimal min, BigDecimal max) {
        List<Product> products = productRepository.findByPriceBetween(min, max);

        if (products.isEmpty()) {
            throw new ProductNotFoundException("No products found with range " + min + " to " + max);
        }

        return products.stream()
                .map(productMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProductResponseDto setProductActiveStatus(Long id, boolean isActive) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id " + id));

        product.setIsActive(isActive);
        Product savedProduct = productRepository.save(product);
        return productMapper.toResponseDto(savedProduct);
    }

    @Override
    public List<ProductSummaryDto> getAllProductsSorted(String sortBy) {
        Sort sort;

        switch(sortBy) {
            case "price-low":
                sort = Sort.by("price").ascending();
                break;
            case "price-high":
                sort = Sort.by("price").descending();
                break;
            case "rating":
                sort = Sort.by("rating").descending();
                break;
            case "popular":
                sort = Sort.by("viewCount").descending();
                break;
            case "newest":
            default:
                sort = Sort.by("createdAt").descending();
                break;
        }

        List<Product> products = productRepository.findAll(sort);
        return products.stream()
                .map(productMapper::toSummaryDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<Product> getProductsByCategories(List<String> categories) {
        return productRepository.findByParentCategoryInIgnoreCase(categories);
    }

    @Override
    public int bulkUpdateStatus(List<Long> productIds, Boolean isActive) {
        int updatedCount = 0;

        for (Long productId : productIds) {
            Optional<Product> productOpt = productRepository.findById(productId);
            if (productOpt.isPresent()) {
                Product product = productOpt.get();
                product.setIsActive(isActive);
                productRepository.save(product);
                updatedCount++;
            }
        }

        return updatedCount;
    }

    @Override
    public void addImages(Long productId, List<String> newImages) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        product.getImageUrls().addAll(newImages);
        productRepository.save(product);
    }

    @Override
    public void removeImages(Long productId, List<String> imagesToRemove) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        product.getImageUrls().removeAll(imagesToRemove);
        productRepository.save(product);
    }

    @Override
    public void reorderImages(Long productId, List<String> reorderedImages) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        product.setImageUrls(new ArrayList<>(reorderedImages));
        productRepository.save(product);
    }

    @Override
    public boolean checkSkuExists(String sku) {
        return productRepository.existsBySku(sku);
    }

    @Override
    public ProductResponseDto recordView(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id " + id));

        product.setViewCount(product.getViewCount() + 1);
        productRepository.save(product);

        return productMapper.toResponseDto(product);
    }

    @Override
    public List<ProductResponseDto> getPopularProducts(int limit) {
        Sort sort = Sort.by("viewCount").descending();
        List<Product> products = productRepository.findAll(sort);

        return products.stream()
                .limit(limit)
                .map(productMapper::toResponseDto)
                .collect(Collectors.toList());
    }
}
