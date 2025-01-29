package dev.sarakutleshi.recipeapp.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "recipes")
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(insertable = false, updatable = false)
    @PositiveOrZero
    private long id;

    @Column(length = 50)
    @NotNull
    private String recipeName;

    @Column(length = 50)
    @NotNull
    private String authorName;

    private String ingredients;

    private String instructions;

    private String steps;

    private String imageUrl;

    private String category;

    @Column(length = 1000)
    private String recipeExplanation;

    @Positive
    @NotNull
    private int minutesToCook;

    @PositiveOrZero
    private Integer hoursToCook = 0;

    @Positive
    private int servings;

    @Positive
    private int calories;

    @Positive
    private String protein;

    @Positive
    private String fat;

    @Positive
    private String carbs;

    @Positive
    private String fiber;


    private boolean deleted;

    private LocalDateTime createdAt = LocalDateTime.now();

    private LocalDateTime modifiedAt;


}