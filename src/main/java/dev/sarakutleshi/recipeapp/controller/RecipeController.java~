package dev.sarakutleshi.recipeapp.controller;

import dev.sarakutleshi.recipeapp.models.Recipe;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RecipeController {

    @GetMapping("/guest-home")
    public String index(Model model) {
        model.addAttribute("pageTitle", "Guest Home Page");
        return "guestHomePage";
    }


    @GetMapping("/about")
    public String about(Model model) {
        model.addAttribute("pageTitle", "About Page");
        return "about";
    }

    @GetMapping("/blog")
    public String contact(Model model) {
        model.addAttribute("pageTitle", "Blog Page");
        return "blog";
    }

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


    @GetMapping("/add-yours")
    public String addYours(Model model) {
        model.addAttribute("pageTitle", "Add Your Recipe");
        return "addYours";
    }

    @GetMapping("/recipe")
    public String viewRecipe(Model model) {
        model.addAttribute("pageTitle", "Recipe");
        return "recipe";
    }

}
