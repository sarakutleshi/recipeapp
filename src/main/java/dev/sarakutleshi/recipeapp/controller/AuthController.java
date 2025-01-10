package dev.sarakutleshi.recipeapp.controller;

import dev.sarakutleshi.recipeapp.dtos.LoginRequestDto;
import dev.sarakutleshi.recipeapp.dtos.RegisterUserRequestDto;
import dev.sarakutleshi.recipeapp.exceptions.EmailExistException;
import dev.sarakutleshi.recipeapp.exceptions.UserNotFoundException;
import dev.sarakutleshi.recipeapp.exceptions.UsernameExistException;
import dev.sarakutleshi.recipeapp.exceptions.WrongPasswordException;
import dev.sarakutleshi.recipeapp.services.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
public class AuthController {
    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/log-in")
    public String login(Model model) {
        model.addAttribute("loginRequestDto", new LoginRequestDto());
        return "auth/login";
    }

    @PostMapping("/log-in")
    public String login(@Valid @ModelAttribute LoginRequestDto loginRequestDto,
                        BindingResult bindingResult,
                        HttpServletRequest request,
                        HttpServletResponse response,
                        @RequestParam(value = "returnUrl", required = false) String returnUrl) {
        if (bindingResult.hasErrors()) {
            return "auth/login";
        }

        try {
            var userDto = userService.login(loginRequestDto.getEmail(), loginRequestDto.getPassword());

            HttpSession session = request.getSession(false); // avoiding creating a new session if one exists
            if (session == null) {
                session = request.getSession(true); // create new session if one doesn't exist
            }
            session.setAttribute("user", userDto);


            Cookie cookie = new Cookie("id", "" + userDto.getId());
            // 30 days or 1 hour
            cookie.setMaxAge(loginRequestDto.isRememberMe() ? 60 * 60 * 24 * 30 : 60 * 60);

            response.addCookie(cookie);

            if (returnUrl == null || returnUrl.isBlank()) {
                return "redirect:/user-home";
            }
            return "redirect:" + returnUrl;

        } catch (UserNotFoundException e) {
            bindingResult.rejectValue("email", "error.loginRequestDto", "User with this email does not exist");
            return "auth/login";
        } catch (WrongPasswordException e) {
            bindingResult.rejectValue("password", "error.loginRequestDto", e.getMessage());
            return "auth/login";
        }
    }


    @GetMapping("/sign-up")
    public String register(Model model) {
        model.addAttribute("registerUserRequestDto", new RegisterUserRequestDto());
        model.addAttribute("maxDate", LocalDate.now().minusYears(18));
        return "auth/signup";
    }

    @PostMapping("/sign-up")
    public String register(@Valid @ModelAttribute RegisterUserRequestDto registerUserRequestDto,
                           BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "auth/signup";
        }

        if (!registerUserRequestDto.getPassword().equals(registerUserRequestDto.getConfirmPassword())) {
            bindingResult.rejectValue("password", "error.registerUserRequestDto", "Passwords do not match");
            bindingResult.rejectValue("confirmPassword", "error.registerUserRequestDto", "Passwords do not match");
            return "auth/signup";
        }

        try {
            userService.register(registerUserRequestDto);
        } catch (UsernameExistException e) {
            bindingResult.rejectValue("username", "error.registerUserRequestDto", "Username already exists");
            return "auth/signup";
        } catch (EmailExistException e) {
            bindingResult.rejectValue("email", "error.registerUserRequestDto", "Email already exists");
            return "auth/signup";
        }

        return "redirect:/log-in";
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
