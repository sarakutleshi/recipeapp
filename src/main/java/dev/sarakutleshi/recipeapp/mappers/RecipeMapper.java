package dev.sarakutleshi.recipeapp.mappers;

import dev.sarakutleshi.recipeapp.dtos.recipes.RecipeDto;
import dev.sarakutleshi.recipeapp.models.Recipe;
import dev.sarakutleshi.recipeapp.models.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Mapper(componentModel = "spring")
public interface RecipeMapper extends Convert<Recipe , RecipeDto> {
    RecipeMapper INSTANCE = Mappers.getMapper(RecipeMapper.class);

    @Mapping(source = "user.id", target = "id")
    @Mapping(source = "createdAt", target = "addedAt")
    @Mapping(source = "user", target = "authorName", qualifiedByName = "fullName")
    @Mapping(source = "createdAt", target = "createdAt", qualifiedByName = "timeAgo")


    @Named("fullName")
    default String fullName(User user) {
        return user.getName() + " " + user.getSurname();
    }

    @Named("timeAgo")
    default String timeAgo(LocalDateTime createdAt) {
        return createdAt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }


}