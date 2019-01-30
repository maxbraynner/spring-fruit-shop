package com.max.fruitshop.controllers.v1;

import com.max.fruitshop.api.v1.model.CategoryDTO;
import com.max.fruitshop.api.v1.model.CategoryListDTO;
import com.max.fruitshop.services.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/api/v1/categories")
public class CategoryController {

    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<CategoryListDTO> getAllCategories() {
        List<CategoryDTO> categories = categoryService.getAllCategories();
        CategoryListDTO categoryListDTO = new CategoryListDTO(categories);

        return new ResponseEntity<>(categoryListDTO, HttpStatus.OK);
    }

    @GetMapping("/{name}")
    public ResponseEntity<CategoryDTO> getByName(@PathVariable String name) {
        CategoryDTO category = categoryService.getCategoryByName(name);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }
}
