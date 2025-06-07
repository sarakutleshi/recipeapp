package dev.sarakutleshi.recipeapp.controller;

import dev.sarakutleshi.recipeapp.dtos.auth.AuthResponse;
import dev.sarakutleshi.recipeapp.dtos.auth.LoginRequestDto;
import dev.sarakutleshi.recipeapp.dtos.recipes.RegisterUserRequestDto;
import dev.sarakutleshi.recipeapp.dtos.user.UserDto;
import dev.sarakutleshi.recipeapp.exceptions.EmailExistException;
import dev.sarakutleshi.recipeapp.exceptions.UsernameExistException;
import dev.sarakutleshi.recipeapp.services.AuthenticationService;
import dev.sarakutleshi.recipeapp.services.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor

public class AuthController {
    private final AuthenticationService service;
    private final UserService userService;

    @GetMapping("/log-in")
    public String login(Model model) {
        model.addAttribute("loginRequestDto", new LoginRequestDto());
        return "auth/login";
    }

    @PostMapping("/log-in")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequestDto request) {
        var user = service.authenticate(request.getEmail(), request.getPassword());
        var token = service.generateToken(user);
        var authResponse = new AuthResponse(token, 86400L); // 86400 seconds = 24 hours
        return ResponseEntity.ok(authResponse);
    }

    @PostMapping("/sign-up")
    public ResponseEntity<Map<String, String>> signUp(@RequestBody UserDto user) {
        Map<String, String> response = new HashMap<>();
        response.put("message", "User registered successfully.");
        return ResponseEntity.ok(response);
    }


    @GetMapping("/sign-up")
    public String register(Model model) {
        model.addAttribute("registerUserRequestDto", new RegisterUserRequestDto());
        return "auth/signup";
    }


    @PostMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        Cookie cookie = new Cookie("id", "");
        cookie.setMaxAge(0);
        response.addCookie(cookie);

        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
            System.out.println("Session invalidated successfully.");
        } else {
            System.out.println("No active session found.");
        }
        return "redirect:/guest-home";
    }




}
