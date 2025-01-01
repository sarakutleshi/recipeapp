package dev.sarakutleshi.recipeapp.services;

import dev.sarakutleshi.recipeapp.dtos.UserDto;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    UserDto login(String email, String password);
//    boolean register(RegisterUserRequestDto registerUserRequestDto);
}
