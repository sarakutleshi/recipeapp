package dev.sarakutleshi.recipeapp.repositories;

import dev.sarakutleshi.recipeapp.models.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {
}
