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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductMapper productMapper;

    @Override
    public ProductResponseDto createProduct(ProductRequestDto productRequest) {
        Product product = productMapper.toEntity(productRequest); // FIX: Use injected mapper
        Product savedProduct = productRepository.save(product);
        return productMapper.toResponseDto(savedProduct); // FIX: Use injected mapper
    }

    @Override
    public ProductResponseDto getProductById(Long id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isEmpty()) {
            throw new ProductNotFoundException("Product not found with id: " + id);
        }
        return productMapper.toResponseDto(product.get()); // FIX: Use injected mapper
    }

    @Override
    public List<ProductResponseDto> getAllProducts() {
        List<Product> products = productRepository.findAll();

        if (products.isEmpty()) {
            throw new ProductNotFoundException("No products found");
        }

        List<ProductResponseDto> responseDtos = new ArrayList<>();
        for (Product product : products) {
            responseDtos.add(productMapper.toResponseDto(product)); // FIX: Use injected mapper
        }

        return responseDtos;
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

        return productMapper.toResponseDto(updatedProduct); // FIX: Use injected mapper
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public Page<ProductResponseDto> getProductsByCategory(String category, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Product> products = productRepository.findByCategoryIgnoreCase(category, pageable);

        if (products.isEmpty()) {
            throw new ProductNotFoundException("No products found in category: " + category);
        }

        return products.map(productMapper::toResponseDto); // FIX: Use injected mapper
    }
    @Override
    public Page<ProductResponseDto> getProductsByParentCategory(String category, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Product> products = productRepository.findByParentCategoryIgnoreCase(category, pageable);

        if (products.isEmpty()) {
            throw new ProductNotFoundException("No products found in category: " + category);
        }

        return products.map(productMapper::toResponseDto); // FIX: Use injected mapper
    }

    @Override
    public Page<ProductResponseDto> getProductsByBrand(String brand, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Product> products = productRepository.findByBrandIgnoreCase(brand, pageable);

        if (products.isEmpty()) {
            throw new ProductNotFoundException("No products found in brand: " + brand);
        }

        return products.map(productMapper::toResponseDto); // FIX: Use injected mapper
    }

    @Override
    public ProductResponseDto updateStock(Long productId, Integer quantity) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id " + productId));

        product.setStockQuantity(quantity);
        Product savedProduct = productRepository.save(product); // FIX: Add save call

        return productMapper.toResponseDto(savedProduct); // FIX: Use injected mapper
    }

    @Override
    public Page<ProductResponseDto> getFeaturedProducts(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Product> featuredProducts = productRepository.findByIsFeaturedTrue(pageable);

        if (featuredProducts.isEmpty()){
            throw new ProductNotFoundException("No featured products found");
        }

        return featuredProducts.map(productMapper::toResponseDto);
    }

    @Override
    public Page<ProductResponseDto> getLowStockProducts(Integer threshold, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Product> lowStockProducts = productRepository.findByStockQuantityLessThan(threshold, pageable);

        if (lowStockProducts.isEmpty()) {
            throw new ProductNotFoundException("No products found with stock less than " + threshold);
        }

        return lowStockProducts.map(productMapper::toResponseDto);

    }

    @Override
    public Page<ProductResponseDto> searchProducts(
            String keyword,
            String category,
            String brand,
            BigDecimal minPrice,
            BigDecimal maxPrice,
            int page,
            int size) {

        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());

        // Query with all filters in DB (better performance & correct pagination)
        Page<Product> productsPage = productRepository.searchProducts(
                keyword == null ? null : keyword.toLowerCase(),
                category,
                brand,
                minPrice,
                maxPrice,
                pageable
        );

        // Map to DTOs
        return productsPage.map(productMapper::toResponseDto);
    }

    @Override
    public Page<ProductResponseDto> getProductsByPriceRange(BigDecimal min, BigDecimal max, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Product> products = productRepository.findByPriceBetween(min, max, pageable);

        if (products.isEmpty()) {
            throw new ProductNotFoundException("No products found with range " + min + " to " + max);
        }

        return products.map(productMapper::toResponseDto); // FIX: Use injected mapper
    }

    @Override
    public ProductResponseDto setProductActiveStatus(Long id, boolean isActive) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id " + id));

        product.setIsActive(isActive);
        Product savedProduct = productRepository.save(product); // FIX: Add save call

        return productMapper.toResponseDto(savedProduct); // FIX: Use injected mapper
    }

    @Override // FIX: Add to interface too
    public Page<ProductSummaryDto> getAllProductsSorted(int page, int size, String sortBy) {
        Pageable pageable;

        switch(sortBy) {
            case "price_asc":
                pageable = PageRequest.of(page, size, Sort.by("price").ascending());
                break;
            case "price_desc":
                pageable = PageRequest.of(page, size, Sort.by("price").descending());
                break;
            case "name_asc":
                pageable = PageRequest.of(page, size, Sort.by("name").ascending());
                break;
            case "newest":
            default:
                pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
                break;
        }

        // FIX: Proper implementation
        Page<Product> products = productRepository.findAll(pageable);
        List<ProductSummaryDto> summaryDtos = new ArrayList<>();
        for (Product product : products.getContent()) {
            summaryDtos.add(productMapper.toSummaryDto(product));
        }

        return new PageImpl<>(summaryDtos, pageable, products.getTotalElements());
    }

    @Override
    public Page<Product> getProductsByCategories(List<String> categories, Pageable pageable) {
        return productRepository.findByParentCategoryInIgnoreCase(categories, pageable);
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

        // Replace with new ordered list
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
        Pageable pageable = PageRequest.of(0, limit, Sort.by("viewCount").descending());
        Page<Product> products = productRepository.findAll(pageable);

        return products.getContent().stream()
                .map(productMapper::toResponseDto)
                .toList();
    }
}
