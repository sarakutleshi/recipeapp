package dev.sarakutleshi.recipeapp.services.impls;


import dev.sarakutleshi.recipeapp.dtos.user.*;
import dev.sarakutleshi.recipeapp.mappers.UserMapper;
import dev.sarakutleshi.recipeapp.models.User;
import dev.sarakutleshi.recipeapp.repositories.UserRepository;
import dev.sarakutleshi.recipeapp.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper mapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public CreateUserRequestDto add(CreateUserRequestDto dto) {
        var entity = mapper.toEntity(dto);
        String encryptedPassword = passwordEncoder.encode(dto.getPassword());
        entity.setPassword(encryptedPassword);

        var savedUser = userRepository.save(entity);

        return mapper.toDto(savedUser);
    }


    @Override
    public List<UserListingDto> findAll() {
        var usersList = userRepository.findAll();
        return mapper.toUsersListingDto(usersList);
    }

    @Override
    public UserDto findById(Long id) {
        Optional<User> user = userRepository.findById(id);
        return mapper.toDetailsDto(user);
    }

    @Override
    public void removeById(Long id) {
        userRepository.deleteById(id);
    }


    @Override
    public UpdateUserRequestDto modify(UpdateUserRequestDto dto, Long id) {
        var userFromDB = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        String email = AuthenticationServiceImpl.getLoggedInUserEmail();

        User adminUser = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Admin user not found"));

        var updatedUser = userRepository.save(userFromDB);
        return mapper.toUpdateDto(updatedUser);
    }

}

