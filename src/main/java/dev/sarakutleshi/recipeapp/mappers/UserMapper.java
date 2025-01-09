package dev.sarakutleshi.recipeapp.mappers;

import dev.sarakutleshi.recipeapp.dtos.RegisterUserRequestDto;
import dev.sarakutleshi.recipeapp.dtos.UserDto;
import dev.sarakutleshi.recipeapp.dtos.UserListingDto;
import dev.sarakutleshi.recipeapp.models.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper extends Convert<User, UserDto> {
    UserListingDto toUserListingDto(User user);
    User userRequestDtoToUser(RegisterUserRequestDto registerUserRequestDto);
}
