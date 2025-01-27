package dev.sarakutleshi.recipeapp.dtos;


import jakarta.validation.constraints.NotEmpty;
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

    @NotEmpty(message = "Recipe name cannot be empty")
    private String recipeName;
    @NotEmpty(message = "Author name cannot be empty")
    private String authorName;
    private String ingredients;
    private String instructions;
    private String steps;
    private String imageUrl;
    private String category;
    private String recipeExplanation;
    private int prepTime;
    private int minutes;
    private int hours;
    private int servings;
    private int calories;
    private int protein;
    private int fat;
    private int carbs;
    private int fiber;

    @NotNull
    private LocalDateTime createdAt = LocalDateTime.now();

    private LocalDateTime modifiedAt;

}
