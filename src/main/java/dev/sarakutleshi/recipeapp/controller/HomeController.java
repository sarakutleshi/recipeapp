package dev.sarakutleshi.recipeapp.controller;

import dev.sarakutleshi.recipeapp.models.Recipe;
import dev.sarakutleshi.recipeapp.services.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class HomeController {

    RecipeService recipeService;

    @Autowired
    public HomeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping("")
    public String home() {
        return "guestHomePage";
    }

    @GetMapping("/guest-home")
    public String index(Model model) {
        model.addAttribute("pageTitle", "Guest Home Page");
        return "guestHomePage";
    }

    @GetMapping("/user-about")
    public String userAbout(Model model) {
        model.addAttribute("pageTitle", "About Page");
        return "user/userAbout";
    }

    @GetMapping("/guest-about")
    public String guestAbout(Model model) {
        model.addAttribute("pageTitle", "About Page");
        return "guest/guestAbout";
    }

    @GetMapping("/guest-blog")
    public String blog(Model model) {
        model.addAttribute("pageTitle", "Blog Page");
        return "guest/guestBlog";
    }

    @GetMapping("/user-blog")
    public String userBlog(Model model) {
        model.addAttribute("pageTitle", "Blog Page");
        return "user/userBlog";
    }


    @GetMapping("/user-recipe")
    public String userRecipe(Model model) {
        model.addAttribute("pageTitle", "Recipe");
        return "user/userRecipe";
    }

    @GetMapping("/guest-recipe")
    public String guestRecipe(Model model) {
        model.addAttribute("pageTitle", "Recipe");
        return "guest/guestRecipe";
    }

    @GetMapping("/view-recipe/{id}")
    public String viewRecipe(@PathVariable("id") Long id, Model model) {
        // Assuming a service method that fetches the recipe from a database
        Recipe recipe = recipeService.findById(id);

        if (recipe != null) {
            model.addAttribute("pageTitle", "View Recipe Page");
            model.addAttribute("recipe", recipe);
        } else {
            model.addAttribute("error", "Recipe not found");
        }
        return "viewRecipe";
    }



}
