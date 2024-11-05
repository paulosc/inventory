package psc.inventory.convert;

import org.springframework.data.domain.Page;
import psc.inventory.dto.PageProductResponse;
import psc.inventory.dto.ProductResponse;
import psc.inventory.entity.Category;
import psc.inventory.entity.Product;
import psc.inventory.service.CategoryService;

import java.util.List;
import java.util.stream.Collectors;

public class ProductConverter {

    private static ProductResponse convertProductToResponse(Product product, CategoryService categoryService) {
        return new ProductResponse(
                product.getId(),
                product.getCategory().getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getAvailable(),
                categoryService.findFullCategoryPathAsString(product.getCategory().getId())
        );
    }

    public static PageProductResponse convertToProductResponseList(Page<Product> productPage,
                                                                   CategoryService categoryService) {
        List<ProductResponse> productResponses = productPage.getContent().stream()
                .map(product -> convertProductToResponse(product, categoryService))
                .collect(Collectors.toList());
        return new PageProductResponse(productResponses, productPage.getTotalPages());
    }

    public static ProductResponse convertToProductResponse(Product product, CategoryService categoryService) {
        return convertProductToResponse(product, categoryService);
    }

    public static Product convertToProduct(ProductResponse productResponse) {
        Product product = new Product();
        product.setName(productResponse.name());
        product.setDescription(productResponse.description());
        product.setPrice(productResponse.price());
        product.setAvailable(productResponse.available());

        Category category = new Category();
        category.setId(productResponse.categoryId());
        product.setCategory(category);

        return product;
    }

}
