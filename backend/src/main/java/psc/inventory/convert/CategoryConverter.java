package psc.inventory.convert;

import psc.inventory.dto.CategoryResponse;
import psc.inventory.entity.Category;

import java.util.List;
import java.util.stream.Collectors;

public class CategoryConverter {

    public static List<CategoryResponse> convertToProductResponseList(List<Category> categories) {
        return categories.stream()
                .map(category -> new CategoryResponse(
                        category.getId(),
                        category.getName()
                ))
                .collect(Collectors.toList());
    }
}
