package dev.sarakutleshi.recipeapp.services;

import dev.sarakutleshi.recipeapp.dtos.RecipeDto;
import org.springframework.stereotype.Service;

@Service
public interface RecipeService extends BaseService<RecipeDto, Long> {
}