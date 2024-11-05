package psc.inventory.controller;

import org.springframework.http.ResponseEntity;
import psc.inventory.dto.CategoryResponse;
import psc.inventory.dto.ProductResponse;
import psc.inventory.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/path/{id}")
    public String getCategoryPath(@PathVariable Long id) {
        return categoryService.findFullCategoryPathAsString(id);
    }

    @GetMapping()
    public ResponseEntity<List<CategoryResponse>> getCategories() {
        return  ResponseEntity.ok(categoryService.findAll());
    }
}



