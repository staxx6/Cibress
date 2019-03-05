package com.datpixelstudio.cibress.dao;


import com.datpixelstudio.cibress.entity.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "ingredients")
public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
}
