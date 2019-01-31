package com.max.fruitshop.controllers.v1;

import com.max.fruitshop.api.v1.model.CategoryDTO;
import com.max.fruitshop.api.v1.model.CategoryListDTO;
import com.max.fruitshop.services.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {

    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public CategoryListDTO getAllCategories() {
        List<CategoryDTO> categories = categoryService.getAllCategories();

        return new CategoryListDTO(categories);
    }

    @GetMapping("/{name}")
    @ResponseStatus(HttpStatus.OK)
    public CategoryDTO getByName(@PathVariable String name) {
        return categoryService.getCategoryByName(name);
    }
}
