package dev.sarakutleshi.recipeapp.dtos;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecipeDto {
    @PositiveOrZero
    private long id;

    @Positive
    private long userId;

    private String recipeName;
    private String authorName;
    private List<String> ingredients;
    private List<String> instructions;
    private List<String> cookingNotes;
    private String imageUrl;
    private String category;
    private String recipeExplanation;
    private int prepTime;
    private int cookingTime;
    private int servings;
    private int calories;
    private String protein;
    private String fat;
    private String carbs;
    private String fiber;
    @NotNull
    private LocalDateTime createdAt = LocalDateTime.now();

    private LocalDateTime modifiedAt;
}
