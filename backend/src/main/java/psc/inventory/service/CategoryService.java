package psc.inventory.service;

import psc.inventory.convert.CategoryConverter;
import psc.inventory.dto.CategoryResponse;
import psc.inventory.entity.Category;
import psc.inventory.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public String findFullCategoryPathAsString(Long categoryId) {
        StringBuilder path = new StringBuilder();
        buildCategoryPath(categoryId, path);
        return path.toString();
    }

    private void buildCategoryPath(Long categoryId, StringBuilder path) {
        Optional<Category> category = categoryRepository.findById(categoryId);
        if (category.isPresent()) {
            if (path.length() > 0) {
                path.insert(0, "/");
            }
            path.insert(0, category.get().getName());

            Long parentId = category.get().getParentId();
            if (parentId != null) {
                buildCategoryPath(parentId, path);
            }
        }
    }

    public List<CategoryResponse> findAll() {
        return CategoryConverter.convertToProductResponseList(categoryRepository.findAll());
    }
}


