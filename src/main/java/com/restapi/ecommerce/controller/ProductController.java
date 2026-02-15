package com.restapi.ecommerce.controller;

import com.restapi.ecommerce.model.Product;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    
    private List<Product> products = new ArrayList<>();
    private Long nextId = 2696516L;

    public ProductController() {
        products.add(new Product(2696501L, "Rodrigue Tech Laptop", "Custom-built laptop by Irakoze", 1899.99, "Electronics", 10, "Rodrigue Tech"));
        products.add(new Product(2696502L, "Google Pixel 8", "Latest Google smartphone with AI", 699.99, "Electronics", 45, "Google"));
        products.add(new Product(2696503L, "OnePlus 11", "Flagship Android phone", 749.99, "Electronics", 35, "OnePlus"));
        products.add(new Product(2696504L, "HP Spectre x360", "Convertible laptop", 1599.99, "Electronics", 18, "HP"));
        products.add(new Product(2696505L, "Lenovo ThinkPad", "Business laptop", 1299.99, "Electronics", 22, "Lenovo"));
        products.add(new Product(2696506L, "Puma RS-X", "Retro running shoes", 110.99, "Footwear", 95, "Puma"));
        products.add(new Product(2696507L, "Reebok Classic", "Casual sneakers", 89.99, "Footwear", 120, "Reebok"));
        products.add(new Product(2696508L, "Bose QuietComfort", "Premium noise-cancelling headphones", 349.99, "Electronics", 0, "Bose"));
        products.add(new Product(2696509L, "Wrangler Jeans", "Authentic western denim", 69.99, "Clothing", 180, "Wrangler"));
        products.add(new Product(2696510L, "Nikon Z6", "Full-frame mirrorless camera", 1999.99, "Electronics", 12, "Nikon"));
        products.add(new Product(2696511L, "Garmin Venu 2", "GPS smartwatch", 399.99, "Electronics", 55, "Garmin"));
        products.add(new Product(2696512L, "Xiaomi Mi Band", "Affordable fitness tracker", 49.99, "Electronics", 200, "Xiaomi"));
        products.add(new Product(2696513L, "New Balance 574", "Classic running shoes", 79.99, "Footwear", 85, "New Balance"));
        products.add(new Product(2696514L, "Zara Jacket", "Stylish winter jacket", 129.99, "Clothing", 60, "Zara"));
        products.add(new Product(2696515L, "Logitech MX Master", "Wireless ergonomic mouse", 99.99, "Electronics", 75, "Logitech"));
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts(@RequestParam(required = false) Integer page, 
                                                         @RequestParam(required = false) Integer limit) {
        if (page != null && limit != null) {
            int start = page * limit;
            int end = Math.min(start + limit, products.size());
            if (start >= products.size()) {
                return ResponseEntity.ok(new ArrayList<>());
            }
            return ResponseEntity.ok(products.subList(start, end));
        }
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<Product> getProductById(@PathVariable Long productId) {
        return products.stream()
                .filter(p -> p.getProductId().equals(productId))
                .findFirst()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<Product>> getProductsByCategory(@PathVariable String category) {
        List<Product> result = products.stream()
                .filter(p -> p.getCategory().equalsIgnoreCase(category))
                .toList();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/brand/{brand}")
    public ResponseEntity<List<Product>> getProductsByBrand(@PathVariable String brand) {
        List<Product> result = products.stream()
                .filter(p -> p.getBrand().equalsIgnoreCase(brand))
                .toList();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Product>> searchProducts(@RequestParam String keyword) {
        List<Product> result = products.stream()
                .filter(p -> p.getName().toLowerCase().contains(keyword.toLowerCase()) ||
                           p.getDescription().toLowerCase().contains(keyword.toLowerCase()))
                .toList();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/price-range")
    public ResponseEntity<List<Product>> getProductsByPriceRange(@RequestParam Double min, @RequestParam Double max) {
        List<Product> result = products.stream()
                .filter(p -> p.getPrice() >= min && p.getPrice() <= max)
                .toList();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/in-stock")
    public ResponseEntity<List<Product>> getInStockProducts() {
        List<Product> result = products.stream()
                .filter(p -> p.getStockQuantity() > 0)
                .toList();
        return ResponseEntity.ok(result);
    }

    @PostMapping
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        product.setProductId(nextId++);
        products.add(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }

    @PutMapping("/{productId}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long productId, @RequestBody Product updatedProduct) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getProductId().equals(productId)) {
                updatedProduct.setProductId(productId);
                products.set(i, updatedProduct);
                return ResponseEntity.ok(updatedProduct);
            }
        }
        return ResponseEntity.notFound().build();
    }

    @PatchMapping("/{productId}/stock")
    public ResponseEntity<Product> updateStock(@PathVariable Long productId, @RequestParam int quantity) {
        for (Product product : products) {
            if (product.getProductId().equals(productId)) {
                product.setStockQuantity(quantity);
                return ResponseEntity.ok(product);
            }
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long productId) {
        boolean removed = products.removeIf(p -> p.getProductId().equals(productId));
        return removed ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
