package com.max.fruitshop.domain;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CategoryTest {

    private Category category;

    @Before
    public void setUp(){
        category = new Category();
    }

    @Test
    public void getId() {
        Long id = 4L;
        category.setId(id);

        assertEquals(category.getId(), id);
    }
}