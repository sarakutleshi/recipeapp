package dev.sarakutleshi.recipeapp.services.baseServices;

public interface FindById<T, Tid> {
    T findById(Tid id);
}