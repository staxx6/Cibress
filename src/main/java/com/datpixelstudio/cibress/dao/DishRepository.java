package com.datpixelstudio.cibress.dao;

import com.datpixelstudio.cibress.entity.Dish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "dishes")
public interface DishRepository extends JpaRepository<Dish, Long> {
    Dish findByName(String name);
}
