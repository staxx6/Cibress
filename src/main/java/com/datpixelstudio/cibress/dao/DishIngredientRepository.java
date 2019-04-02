package com.datpixelstudio.cibress.dao;

import com.datpixelstudio.cibress.entity.Dish;
import com.datpixelstudio.cibress.entity.DishIngredient;
import com.datpixelstudio.cibress.entity.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DishIngredientRepository extends JpaRepository<DishIngredient, Long> {
    DishIngredient findByDishAndIngredient(Dish dish, Ingredient ingredient);
}
