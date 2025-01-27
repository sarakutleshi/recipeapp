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

    @GetMapping("/add-yours")
    public String addYours(Model model) {
        model.addAttribute("pageTitle", "Add Your Recipe");
        return "addYours";  // This should return the 'new.html' view
    }

}
