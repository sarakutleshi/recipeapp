package dev.sarakutleshi.recipeapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("")
    public String home() {
        return "guestHomePage";
    }

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

    @GetMapping("/add-yours")
    public String addYours(Model model) {
        model.addAttribute("pageTitle", "Add Your Recipe");
        return "/addYours";
    }

    @GetMapping("/recipe")
    public String viewRecipe(Model model) {
        model.addAttribute("pageTitle", "Recipe");
        return "recipe";
    }
}
