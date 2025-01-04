package dev.sarakutleshi.recipeapp.mappers;

import dev.sarakutleshi.recipeapp.dtos.RegisterUserRequestDto;
import dev.sarakutleshi.recipeapp.dtos.UserDto;
import dev.sarakutleshi.recipeapp.dtos.UserListingDto;
import dev.sarakutleshi.recipeapp.models.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapperImpl implements UserMapper {
    @Override
    public UserDto toDto(User user) {
        return new UserDto(user.getId(), user.getUsername(), user.getEmail(), user.getName(), user.getSurname(), user.getBirthdate());
    }

    @Override
    public User toEntity(UserDto userDto) {
        User user = new User();
        user.setId(userDto.getId());
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setName(userDto.getName());
        user.setSurname(userDto.getSurname());
        user.setBirthdate(userDto.getBirthdate());
        return user;
    }

    @Override
    public UserListingDto toUserListingDto(User user) {
        UserListingDto dto = new UserListingDto();
        dto.setEmail(user.getEmail());
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        return dto;
    }

    @Override
    public User userRequestDtoToUser(RegisterUserRequestDto registerUserRequestDto) {
        User user = new User();
        user.setUsername(registerUserRequestDto.getUsername());
        user.setName(registerUserRequestDto.getName());
        user.setSurname(registerUserRequestDto.getSurname());
        user.setEmail(registerUserRequestDto.getEmail());
        user.setBirthdate(registerUserRequestDto.getBirthdate());
        return user;
    }
}
