package dev.sarakutleshi.recipeapp.dtos.user;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    @PositiveOrZero(message = "User ID must be positive or zero")
    @NotBlank
    private long id;
    @NotBlank
    private String username;
    @NotBlank
    private String email;
    @NotBlank
    private String name;
    @NotBlank
    private String surname;
}

