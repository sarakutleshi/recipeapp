package dev.sarakutleshi.recipeapp.controller;

import dev.sarakutleshi.recipeapp.models.Recipe;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RecipeController {

    @GetMapping("/view-recipe")
    public String stats(Model model) {
        Recipe recipe = new Recipe();

        model.addAttribute("pageTitle", "View Recipe Page");
        model.addAttribute("recipeName", recipe.getRecipeName());
        model.addAttribute("authorName", recipe.getAuthorName());
        model.addAttribute("ingredients", recipe.getIngredients());
        model.addAttribute("instructions", recipe.getInstructions());
        model.addAttribute("prepTime", recipe.getPrepTime());
        model.addAttribute("cookingTime", recipe.getCookingTime());
        model.addAttribute("servings", recipe.getServings());
        model.addAttribute("cookingNotes", recipe.getCookingNotes());
        model.addAttribute("recipeExplanation", recipe.getRecipeExplanation());
        model.addAttribute("calories", recipe.getCalories());
        model.addAttribute("protein", recipe.getProtein());
        model.addAttribute("fat", recipe.getFat());
        model.addAttribute("carbs", recipe.getCarbs());
        model.addAttribute("fiber", recipe.getFiber());
        model.addAttribute("recipe", recipe);

        return "viewRecipe";
    }


}
