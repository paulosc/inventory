package psc.inventory.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import psc.inventory.convert.ProductConverter;
import psc.inventory.dto.PageProductResponse;
import psc.inventory.dto.ProductResponse;
import psc.inventory.entity.Product;
import psc.inventory.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryService categoryService;

    public PageProductResponse getAllProducts(Pageable pageable) {
        return ProductConverter.convertToProductResponseList(productRepository.findAll(pageable), categoryService);
    }

    public PageProductResponse getProductsByName(String name, Pageable pageable) {
        return ProductConverter.convertToProductResponseList(productRepository.findByNameContaining(name, pageable), categoryService);
    }

    public PageProductResponse getProductsByAvailability(Boolean available, Pageable pageable) {
        return ProductConverter.convertToProductResponseList(productRepository.findByAvailable(available, pageable), categoryService);
    }

    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
