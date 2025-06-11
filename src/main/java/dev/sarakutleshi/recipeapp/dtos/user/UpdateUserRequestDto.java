package dev.sarakutleshi.recipeapp.dtos.user;

import dev.sarakutleshi.recipeapp.models.Role;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserRequestDto {
    @Positive
    private Long id;
    private Role role;
}