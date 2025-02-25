package dev.sarakutleshi.recipeapp.services;

import dev.sarakutleshi.recipeapp.dtos.RegisterUserRequestDto;
import dev.sarakutleshi.recipeapp.dtos.UserDto;
import dev.sarakutleshi.recipeapp.exceptions.EmailExistException;
import dev.sarakutleshi.recipeapp.exceptions.UserNotFoundException;
import dev.sarakutleshi.recipeapp.exceptions.UsernameExistException;
import dev.sarakutleshi.recipeapp.exceptions.WrongPasswordException;
import dev.sarakutleshi.recipeapp.mappers.UserMapper;
import dev.sarakutleshi.recipeapp.models.User;
import dev.sarakutleshi.recipeapp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final UserMapper userMapperImpl;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository repository, UserMapper userMapperImpl, PasswordEncoder passwordEncoder) {
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
        if (repository.findByUsername(registerUserRequestDto.getUsername()).isPresent()) {
            throw new UsernameExistException();
        }
        if (repository.findByEmail(registerUserRequestDto.getEmail()).isPresent()) {
            throw new EmailExistException();
        }

        User user = userMapperImpl.userRequestDtoToUser(registerUserRequestDto);
        user.setPassword(passwordEncoder.encode(registerUserRequestDto.getPassword()));
        repository.save(user);

        return true;
    }
}
