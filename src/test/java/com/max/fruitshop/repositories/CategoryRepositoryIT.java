package com.max.fruitshop.repositories;

import com.max.fruitshop.domain.Category;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CategoryRepositoryIT {

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    public void findByNameFruits() {
        Optional<Category> category = categoryRepository.findByName("Fruits");

        assertEquals("Fruits", category.get().getName());
    }

    @Test
    public void findByNameDried() {
        Optional<Category> category = categoryRepository.findByName("Dried");

        assertEquals("Dried", category.get().getName());
    }
}