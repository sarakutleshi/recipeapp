package dev.sarakutleshi.recipeapp.mappers;

import dev.sarakutleshi.recipeapp.dtos.UserDto;
import dev.sarakutleshi.recipeapp.models.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper extends SimpleMapper<User, UserDto> {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

}
