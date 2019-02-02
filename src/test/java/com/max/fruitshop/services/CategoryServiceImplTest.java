package com.max.fruitshop.services;

import com.max.fruitshop.api.v1.mapper.CategoryMapper;
import com.max.fruitshop.api.v1.model.CategoryDTO;
import com.max.fruitshop.domain.Category;
import com.max.fruitshop.repositories.CategoryRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

public class CategoryServiceImplTest {

    private final String NAME = "Max";

    @InjectMocks
    private CategoryServiceImpl categoryService;

    @Mock
    private CategoryRepository categoryRepository;

    private final CategoryMapper categoryMapper = CategoryMapper.INSTANCE;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        categoryService = new CategoryServiceImpl(categoryRepository, categoryMapper);

        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setName(NAME);

        Category category = new Category();
        category.setName(NAME);

        ArrayList<Category> categoriesData = new ArrayList<>();
        categoriesData.add(category);

        Mockito.when(categoryRepository.findAll()).thenReturn(categoriesData);
        Mockito.when(categoryRepository.findByName(NAME)).thenReturn(Optional.of(category));
    }

    @Test
    public void getAllCategories() {
        List<CategoryDTO> allCategories = categoryService.getAllCategories();

        assertEquals(allCategories.size(), 1);
    }

    @Test
    public void getCategoryByName() {
        CategoryDTO categoryByName = categoryService.getCategoryByName(NAME);

        assertEquals(categoryByName.getName(), NAME);
    }
}