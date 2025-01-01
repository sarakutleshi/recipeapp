package dev.sarakutleshi.recipeapp.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    @Column(insertable=false, updatable=false)
    @PositiveOrZero
    private long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(length = 50)
    @NotNull
    private String recipeName;

    @Column(length = 50)
    @NotNull
    private String authorName;

    @ElementCollection
    @CollectionTable(joinColumns = @JoinColumn())
    private List<String> ingredients;

    @ElementCollection
    @CollectionTable(joinColumns = @JoinColumn())
    private List<String> instructions;

    @ElementCollection
    @CollectionTable(joinColumns = @JoinColumn())
    private List<String> cookingNotes;

    @Column(length = 500)
    private String imageUrl;

    private String category;

    @Column(length = 1000)
    private String recipeExplanation;

    @Positive
    @NotNull
    private int prepTime;

    @Positive
    @NotNull
    private int cookingTime;

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

    private boolean accessible;

    private boolean deleted;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    private LocalDateTime modifiedAt;


}
