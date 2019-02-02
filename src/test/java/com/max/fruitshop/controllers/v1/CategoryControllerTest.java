package com.max.fruitshop.controllers.v1;

import com.max.fruitshop.api.v1.model.CategoryDTO;
import com.max.fruitshop.services.CategoryService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CategoryControllerTest {

    private static final String NAME = "Max";

    @Mock
    private CategoryService service;

    @InjectMocks
    private CategoryController controller;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void getAllCategories() throws Exception {
        CategoryDTO category1 = new CategoryDTO();
        category1.setId(1L);

        CategoryDTO category2 = new CategoryDTO();
        category2.setId(2L);

        List<CategoryDTO> categoryDTOS = Arrays.asList(category1, category2);

        when(service.getAllCategories()).thenReturn(categoryDTOS);

        mockMvc.perform(get(CategoryController.BASE_URL)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.categories", hasSize(2)));
    }

    @Test
    public void getByName() throws Exception {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(1L);
        categoryDTO.setName(NAME);

        when(service.getCategoryByName(NAME)).thenReturn(categoryDTO);

        mockMvc.perform(get(CategoryController.BASE_URL + "/" + NAME)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo(NAME)));
    }
}