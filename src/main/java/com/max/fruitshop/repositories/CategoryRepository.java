package com.max.fruitshop.repositories;

import com.max.fruitshop.domain.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Long> {

}
