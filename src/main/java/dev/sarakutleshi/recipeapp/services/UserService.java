package dev.sarakutleshi.recipeapp.services;

import org.springframework.stereotype.Service;

import dev.sarakutleshi.recipeapp.dtos.recipes.RegisterUserRequestDto;
import dev.sarakutleshi.recipeapp.dtos.user.UserDto;

@Service
public interface UserService {
    UserDto login(String email, String password);
    boolean register(RegisterUserRequestDto registerUserRequestDto);
}
