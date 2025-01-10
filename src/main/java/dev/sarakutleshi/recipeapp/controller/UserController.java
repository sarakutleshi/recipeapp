package dev.sarakutleshi.recipeapp.controller;

import dev.sarakutleshi.recipeapp.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {
    // me i mar te dhenat e userit
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user-home")
    public String userHome(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);
        model.addAttribute("pageTitle", "User Home Page");
        // false sherben per mos me kriju session te ri nese nuk ekziston
        if (session == null || session.getAttribute("user") == null) {
            return "redirect:/guest-home"; //nese nuk eshte log in te dergon te guest home
        }
        return "userHomePage";
    }

}

