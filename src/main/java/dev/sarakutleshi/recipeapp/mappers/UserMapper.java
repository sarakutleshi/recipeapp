package dev.sarakutleshi.recipeapp.mappers;

import dev.sarakutleshi.recipeapp.dtos.user.CreateUserRequestDto;
import dev.sarakutleshi.recipeapp.dtos.user.UpdateUserRequestDto;
import dev.sarakutleshi.recipeapp.dtos.user.UserDto;
import dev.sarakutleshi.recipeapp.dtos.user.UserListingDto;
import dev.sarakutleshi.recipeapp.models.User;
import org.mapstruct.Mapper;
import org.springframework.context.annotation.Primary;

import java.util.List;
import java.util.Optional;


@Primary
@Mapper(componentModel = "spring")
public interface UserMapper extends Convert<User, CreateUserRequestDto> {
    UserDto toDetailsDto(Optional<User> userEntity);

    List<UserListingDto> toUsersListingDto(List<User> users);

    UpdateUserRequestDto toUpdateDto(User userEntity);

}

