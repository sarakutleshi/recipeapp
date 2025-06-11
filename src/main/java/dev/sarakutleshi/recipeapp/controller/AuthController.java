package dev.sarakutleshi.recipeapp.controller;

import dev.sarakutleshi.recipeapp.dtos.auth.AuthResponse;
import dev.sarakutleshi.recipeapp.dtos.auth.LoginRequestDto;
import dev.sarakutleshi.recipeapp.dtos.user.CreateUserRequestDto;
import dev.sarakutleshi.recipeapp.services.AuthenticationService;
import dev.sarakutleshi.recipeapp.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationService service;
    private final UserService userService;

    @PostMapping("/log-in")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequestDto request) {
        var user = service.authenticate(request.getEmail(), request.getPassword());
        var token = service.generateToken(user);
        var authResponse = new AuthResponse(token, 86400L); // 86400 seconds = 24 hours
        return ResponseEntity.ok(authResponse);
    }

    @PostMapping("/sign-up")
    public ResponseEntity<CreateUserRequestDto> register(@Valid @RequestBody CreateUserRequestDto createUserRequestDto
            , BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(createUserRequestDto);
        } else {
            CreateUserRequestDto createdUser = userService.add(createUserRequestDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
        }
    }

}
