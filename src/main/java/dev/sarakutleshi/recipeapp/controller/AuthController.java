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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

            HttpSession session = request.getSession();
            session.setAttribute("user", userDto);

            Cookie cookie = new Cookie("id", "" + userDto.getId());
            if (loginRequestDto.isRememberMe()) {
                cookie.setMaxAge(60 * 60 * 24 * 30); // 30 days
            } else {
                cookie.setMaxAge(60 * 60); // 1 hour
            }

            response.addCookie(cookie);

            if (returnUrl == null || returnUrl.isBlank())
                return "redirect:/userHomePage";
            return "redirect:" + returnUrl;
        } catch (UserNotFoundException e) {
            bindingResult.rejectValue("email", "error.loginRequestDto",
                    "User with this email does not exist");
            return "auth/login";
        } catch (WrongPasswordException e) {
            bindingResult.rejectValue("password", "error.loginRequestDto",
                    e.getMessage());
            return "auth/login";
        }
    }


    @GetMapping("/sign-up")
    public String register(Model model) {
        model.addAttribute("registerUserRequestDto", new RegisterUserRequestDto());
        model.addAttribute("maxDate", LocalDate.now().minusYears(18));
        return "signup";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute RegisterUserRequestDto registerUserRequestDto,
                           BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "auth/register";
        }

        if (!registerUserRequestDto.getPassword().equals(registerUserRequestDto.getConfirmPassword())) {
            bindingResult.rejectValue("password", "error.registerUserRequestDto", "Passwordet nuk perputhen");
            bindingResult.rejectValue("confirmPassword", "error.registerUserRequestDto", "Passwordet nuk perputhen");
            return "auth/register";
        }

        try {
            userService.register(registerUserRequestDto);
        } catch (UsernameExistException e) {
            bindingResult.rejectValue("username", "error.registerUserRequestDto",
                    "Ky username ekziston");
            return "auth/register";
        } catch (EmailExistException e) {
            bindingResult.rejectValue("email", "error.registerUserRequestDto",
                    "Ky email ekziston");
            return "auth/register";
        }


        return "redirect:/login";
    }
}
