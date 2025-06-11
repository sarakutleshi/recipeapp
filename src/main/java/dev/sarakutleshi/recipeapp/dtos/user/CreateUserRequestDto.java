package dev.sarakutleshi.recipeapp.dtos.user;


import dev.sarakutleshi.recipeapp.models.Role;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class CreateUserRequestDto {
    @Positive
    private Long id;

    @NotBlank(message = "First name must not be empty")
    @NotNull(message = "First name must not be null")
    @Size(min = 2, max = 50, message = "First name must be between 2 and 50 characters")
    private String name;

    @NotNull(message = "Last name must not be null")
    @NotBlank(message = "Last name must not be empty")
    @Size(min = 2, max = 50, message = "Last name must be between 2 and 50 characters")
    private String surname;

    @NotNull
    @NotBlank
    @Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters")
    private String username;

    @Email(message = "Invalid email format")
    @NotBlank(message = "Email must not be empty")
    @NotNull(message = "Email must not be null")
    private String email;

    @NotNull(message = "Password must not be null")
    @NotBlank(message = "Password must not be empty")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d).*$", message = "Password must contain at least one uppercase letter, one lowercase letter, and one number")
    private String password;

    @NotNull(message = "Confirm password must not be null")
    @NotBlank(message = "Confirm password must not be empty")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d).*$", message = "Confirm Password must contain at least one uppercase letter, one lowercase letter, and one number")
    private String confirmPassword;


    private Role role;
}
