package com.max.fruitshop.services;

import com.max.fruitshop.api.v1.mapper.CategoryMapper;
import com.max.fruitshop.api.v1.model.CategoryDTO;
import com.max.fruitshop.domain.Category;
import com.max.fruitshop.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public CategoryServiceImpl(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    @Override
    public List<CategoryDTO> getAllCategories() {
        return categoryRepository.findAll()
                .stream()
                .map(categoryMapper::categoryToCategoryDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CategoryDTO getCategoryByName(String name) {
        Optional<Category> category = categoryRepository.findByName(name);
        return categoryMapper.categoryToCategoryDTO(category.get());
    }
}
