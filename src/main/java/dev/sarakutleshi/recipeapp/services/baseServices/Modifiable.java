package dev.sarakutleshi.recipeapp.services.baseServices;

public interface Modifiable<T, Tid> {
    T modify(T entity, Tid id);
}