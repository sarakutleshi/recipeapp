package dev.sarakutleshi.recipeapp.services;

import dev.sarakutleshi.recipeapp.models.Recipe;
import dev.sarakutleshi.recipeapp.repositories.RecipeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecipeService {
    private final RecipeRepository recipeRepository;

    public RecipeService(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }


    public List<Recipe> findAll() {
        return recipeRepository.findAll();
    }

    public Recipe findById(long id) {
        return recipeRepository.findById(id).orElse(null);
    }

    public Recipe add(Recipe customer) {
        var existCustomer = findById(customer.getId());
        if (existCustomer != null) {
            return null;
        }
        return recipeRepository.save(customer);
    }

    public Recipe modify(long id, Recipe customer) {
        var existCustomer = findById(customer.getId());
        if (existCustomer == null) {
            return null;
        }
        return recipeRepository.save(customer);
    }

    public void deleteById(long id) {
        var existCustomer = findById(id);
        if (existCustomer == null) {
            System.out.println("Recipe with id = " + id + " does not exist");
            return;
        }
        recipeRepository.deleteById(id);
    }

    public void removeById(long id) {
        recipeRepository.deleteById(id);
    }

    public List<Recipe> searchByName(String name) {
        return recipeRepository.findByRecipeNameContainingIgnoreCase(name);
    }


}