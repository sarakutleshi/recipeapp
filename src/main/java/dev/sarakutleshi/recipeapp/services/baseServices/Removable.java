package dev.sarakutleshi.recipeapp.services.baseServices;

public interface Removable<Tid> {
    void removeById(Tid id);
}