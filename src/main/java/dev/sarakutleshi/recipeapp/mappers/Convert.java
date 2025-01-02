package dev.sarakutleshi.recipeapp.mappers;

public interface Convert<TEntity, TDto> {
    TEntity toEntity(TDto dto);

    TDto toDto(TEntity entity);

}
