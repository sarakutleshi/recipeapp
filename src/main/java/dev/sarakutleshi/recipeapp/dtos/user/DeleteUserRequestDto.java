package dev.sarakutleshi.recipeapp.dtos.user;

import dev.sarakutleshi.recipeapp.models.Role;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeleteUserRequestDto {
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
    @Size(min = 3, max = 50, message = "Username should be between 3 and 50 characters")
    private String username;

    @Email(message = "Invalid email format")
    @NotBlank(message = "Email must not be empty")
    @NotNull(message = "Email must not be null")
    private String email;


    private Role role;

}
