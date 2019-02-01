package com.max.fruitshop.controllers.v1;

import com.max.fruitshop.services.CategoryService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CategoryControllerTest {

    @Mock
    private CategoryService service;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        CategoryController controller = new CategoryController(service);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void getAllCategories() throws Exception {
        mockMvc.perform(get(CategoryController.BASE_URL))
                .andExpect(status().isOk());
    }

    @Test
    public void getByName() throws Exception {
        mockMvc.perform(get(CategoryController.BASE_URL + "/max"))
                .andExpect(status().isOk());
    }
}