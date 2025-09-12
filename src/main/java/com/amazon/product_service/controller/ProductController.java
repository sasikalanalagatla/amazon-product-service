package com.amazon.product_service.controller;

import com.amazon.product_service.dto.ProductRequestDto;
import com.amazon.product_service.dto.ProductResponseDto;
import com.amazon.product_service.dto.ProductSummaryDto;
import com.amazon.product_service.model.Product;
import com.amazon.product_service.service.impl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductServiceImpl productService;

    @PostMapping("/create")
    public ResponseEntity<ProductResponseDto> createProduct(@RequestBody ProductRequestDto productDto) {
        ProductResponseDto createdProduct = productService.createProduct(productDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDto> getProductById(@PathVariable Long id) {
        ProductResponseDto productFound = productService.getProductById(id);
        return ResponseEntity.ok(productFound);
    }

    @GetMapping("/all")
    public ResponseEntity<List<ProductResponseDto>> getAllProducts(){
        List<ProductResponseDto> allProducts = productService.getAllProducts();
        return ResponseEntity.ok(allProducts);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<ProductResponseDto> updateProduct(@RequestBody ProductRequestDto productRequest,
                                                            @PathVariable Long id){
        ProductResponseDto product = productService.updateProduct(productRequest,id);
        return ResponseEntity.status(HttpStatus.OK).body(product);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/category/{categoryName}")
    public ResponseEntity<List<ProductResponseDto>> getProductsByCategory(@PathVariable String categoryName) {
        List<ProductResponseDto> products = productService.getProductsByCategory(categoryName);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/parent-category/{categoryName}")
    public ResponseEntity<List<ProductResponseDto>> getProductsByParentCategory(@PathVariable String categoryName) {
        List<ProductResponseDto> products = productService.getProductsByParentCategory(categoryName);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/brand/{brand}")
    public ResponseEntity<List<ProductResponseDto>> getProductsByBrand(@PathVariable String brand) {
        List<ProductResponseDto> products = productService.getProductsByBrand(brand);
        return ResponseEntity.ok(products);
    }

    @PutMapping("/{id}/stock")
    public ResponseEntity<ProductResponseDto> updateStock(
            @PathVariable Long id,
            @RequestBody Integer quantity) {

        ProductResponseDto updatedProduct = productService.updateStock(id, quantity);
        return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
    }

    @GetMapping("/featured")
    public ResponseEntity<List<ProductResponseDto>> getFeaturedProducts(){
        List<ProductResponseDto> featuredProducts = productService.getFeaturedProducts();
        return ResponseEntity.ok(featuredProducts);
    }

    @GetMapping("/low-stock")
    public ResponseEntity<List<ProductResponseDto>> getLowStockProducts(@RequestParam Integer threshold) {
        List<ProductResponseDto> lowStockProducts = productService.getLowStockProducts(threshold);
        return ResponseEntity.ok(lowStockProducts);
    }

    @GetMapping("/price-range")
    public ResponseEntity<List<ProductResponseDto>> getProductsByPriceRange(
            @RequestParam BigDecimal min,
            @RequestParam BigDecimal max) {
        List<ProductResponseDto> products = productService.getProductsByPriceRange(min, max);
        return ResponseEntity.ok(products);
    }

    @PutMapping("/activate/{id}")
    public ResponseEntity<ProductResponseDto> activateProduct(@PathVariable Long id){
        ProductResponseDto activateProduct = productService.setProductActiveStatus(id, true);
        return ResponseEntity.ok(activateProduct);
    }

    @PutMapping("/de-activate/{id}")
    public ResponseEntity<ProductResponseDto> deActivateProduct(@PathVariable Long id){
        ProductResponseDto deActivateProduct = productService.setProductActiveStatus(id, false);
        return ResponseEntity.ok(deActivateProduct);
    }

    @GetMapping("/")
    public List<ProductSummaryDto> getAllProductsSorted(@RequestParam(defaultValue = "newest") String sort) {
        return productService.getAllProductsSorted(sort);
    }

    @GetMapping("/search")
    public ResponseEntity<List<ProductResponseDto>> searchProducts(
            @RequestParam String keyword,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String brand,
            @RequestParam(required = false) BigDecimal minPrice,
            @RequestParam(required = false) BigDecimal maxPrice) {

        List<ProductResponseDto> results = productService.searchProducts(
                keyword, category, brand, minPrice, maxPrice
        );

        return ResponseEntity.ok(results);
    }

    @GetMapping("/categories")
    public ResponseEntity<List<Product>> getProductsByCategories(@RequestParam List<String> categories) {
        List<Product> products = productService.getProductsByCategories(categories);
        return ResponseEntity.ok(products);
    }

    @PutMapping("/bulk-status")
    public ResponseEntity<String> bulkUpdateStatus(
            @RequestParam List<Long> productIds,
            @RequestParam Boolean isActive) {

        int count = productService.bulkUpdateStatus(productIds, isActive);
        return ResponseEntity.ok("Updated " + count + " products");
    }

    @PostMapping("/{id}/images")
    public ResponseEntity<String> addImages(
            @PathVariable Long id,
            @RequestBody List<String> newImages) {
        productService.addImages(id, newImages);
        return ResponseEntity.ok("Images added successfully!");
    }

    @DeleteMapping("/{id}/images")
    public ResponseEntity<String> removeImages(
            @PathVariable Long id,
            @RequestBody List<String> imagesToRemove) {
        productService.removeImages(id, imagesToRemove);
        return ResponseEntity.ok("Images removed successfully!");
    }

    @PutMapping("/{id}/images/reorder")
    public ResponseEntity<String> reorderImages(
            @PathVariable Long id,
            @RequestBody List<String> reorderedImages) {
        productService.reorderImages(id, reorderedImages);
        return ResponseEntity.ok("Images reordered successfully!");
    }

    @GetMapping("/check-sku/{sku}")
    public ResponseEntity<Boolean> checkSkuExists(@PathVariable String sku) {
        boolean exists = productService.checkSkuExists(sku);
        return ResponseEntity.ok(exists);
    }

    @PostMapping("/{id}/view")
    public ResponseEntity<ProductResponseDto> recordView(@PathVariable Long id) {
        ProductResponseDto updatedProduct = productService.recordView(id);
        return ResponseEntity.ok(updatedProduct);
    }

    @GetMapping("/popular")
    public ResponseEntity<List<ProductResponseDto>> getPopularProducts(
            @RequestParam(defaultValue = "5") int limit) {
        List<ProductResponseDto> popularProducts = productService.getPopularProducts(limit);
        return ResponseEntity.ok(popularProducts);
    }
}