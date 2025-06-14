package dev.sarakutleshi.recipeapp.dtos.recipes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ListingRecipeDto {
    private Long id;
    private String recipeName;
    private String authorFullName;
    private List<String> ingredients;
    private List<String> instructions;
    private String category;
    private String imageUrl;
    private String addedAt;
    private String addedAgo;
    private int prepTime;
    private int cookingTime;
    private int servings;
    private int calories;
    private String protein;
    private String fat;
    private String carbs;
    private String fiber;
    private boolean accessible;
}

