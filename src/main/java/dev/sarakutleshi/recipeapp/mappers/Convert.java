package dev.sarakutleshi.recipeapp.mappers;

import dev.sarakutleshi.recipeapp.dtos.user.UserDto;

import java.util.List;

public interface Convert<TEntity, TDto> {
    TDto toDto(TEntity entity);

    TEntity toEntity(TDto dto);

    List<TDto> toDtoList(List<TEntity> entityList);

    List<TEntity> toEntityList(List<TDto> dtoList);

}
