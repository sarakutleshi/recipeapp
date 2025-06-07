package dev.sarakutleshi.recipeapp.mappers;

import dev.sarakutleshi.recipeapp.dtos.recipes.RegisterUserRequestDto;
import dev.sarakutleshi.recipeapp.dtos.user.UserDto;
import dev.sarakutleshi.recipeapp.dtos.user.UserListingDto;
import dev.sarakutleshi.recipeapp.models.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper extends Convert<User, UserDto> {
    UserListingDto toUserListingDto(User user);
    User userRequestDtoToUser(RegisterUserRequestDto registerUserRequestDto);
}
