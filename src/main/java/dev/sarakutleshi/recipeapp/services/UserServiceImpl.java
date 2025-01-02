package dev.sarakutleshi.recipeapp.services;

import dev.sarakutleshi.recipeapp.dtos.RegisterUserRequestDto;
import dev.sarakutleshi.recipeapp.dtos.UserDto;
import dev.sarakutleshi.recipeapp.exceptions.UserNotFoundException;
import dev.sarakutleshi.recipeapp.exceptions.WrongPasswordException;
import dev.sarakutleshi.recipeapp.mappers.UserMapperImpl;
import dev.sarakutleshi.recipeapp.models.User;
import dev.sarakutleshi.recipeapp.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository repository;
    private final UserMapperImpl userMapperImpl;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository repository, UserMapperImpl userMapperImpl, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.userMapperImpl = userMapperImpl;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public UserDto login(String email, String password) {
        User user = repository.findByEmail(email).orElse(null);

        if (user == null) {
            throw new UserNotFoundException();
        }
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new WrongPasswordException();
        }

        return userMapperImpl.toDto(user);
    }

    @Override
    public boolean register(RegisterUserRequestDto registerUserRequestDto) {
        return false;
    }
}
