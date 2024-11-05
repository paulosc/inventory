package psc.inventory.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import psc.inventory.convert.ProductConverter;
import psc.inventory.dto.PageProductResponse;
import psc.inventory.dto.ProductResponse;
import psc.inventory.entity.Product;
import psc.inventory.service.CategoryService;
import psc.inventory.service.ProductService;

import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public ResponseEntity<PageProductResponse> getProducts(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Boolean available,
            Pageable pageable) {
        if (name != null) {
            return ResponseEntity.ok(productService.getProductsByName(name, pageable));
        } else if (available != null) {
            return ResponseEntity.ok(productService.getProductsByAvailability(available, pageable));
        } else {
            return ResponseEntity.ok(productService.getAllProducts(pageable));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProduct(@PathVariable Long id) {
        Optional<Product> product = productService.getProductById(id);
        return product.map(prod -> ResponseEntity.ok(ProductConverter.convertToProductResponse(prod, categoryService)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ProductResponse> addProduct(@RequestBody ProductResponse productResponse) {
        Product product = ProductConverter.convertToProduct(productResponse);
        Product savedProduct = productService.saveProduct(product);
        ProductResponse response = ProductConverter.convertToProductResponse(savedProduct, categoryService);
        return ResponseEntity.status(201).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product productDetails) {
        Optional<Product> product = productService.getProductById(id);
        if (product.isPresent()) {
            Product updatedProduct = product.get();
            updatedProduct.setName(productDetails.getName());
            updatedProduct.setDescription(productDetails.getDescription());
            updatedProduct.setPrice(productDetails.getPrice());
            updatedProduct.setCategory(productDetails.getCategory());
            updatedProduct.setAvailable(productDetails.getAvailable());
            return ResponseEntity.ok(productService.saveProduct(updatedProduct));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}

