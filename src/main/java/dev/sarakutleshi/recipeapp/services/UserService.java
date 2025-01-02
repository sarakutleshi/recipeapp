package dev.sarakutleshi.recipeapp.services;

import org.springframework.stereotype.Service;

import dev.sarakutleshi.recipeapp.dtos.RegisterUserRequestDto;
import dev.sarakutleshi.recipeapp.dtos.UserDto;

@Service
public interface UserService {
    UserDto login(String email, String password);
    boolean register(RegisterUserRequestDto registerUserRequestDto);
}
