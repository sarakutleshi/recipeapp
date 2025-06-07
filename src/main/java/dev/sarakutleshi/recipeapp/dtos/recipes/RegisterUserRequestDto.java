package dev.sarakutleshi.recipeapp.dtos.recipes;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterUserRequestDto {

    @Size(min = 3, max = 50, message = "Username should be between 3 and 50 characters")
    @NotBlank(message = "Username should not be empty or blank")
    @NotNull(message = "Username is required")
    private String username;

    @Size(min = 3, max = 50, message = "Email should be between 3 and 50 characters")
    @NotBlank(message = "Email should not be empty or blank")
    @Email(message = "Email should be valid")
    private String email;

    @Size(min = 3, max = 50, message = "Name should be between 3 and 50 characters")
    @NotBlank(message = "Name should not be empty or blank")
    @NotNull(message = "Name is required")
    private String name;

    @Size(min = 3, max = 50, message = "Surname should be between 3 and 50 characters")
    @NotBlank(message = "Surname should not be empty or blank")
    @NotNull(message = "Surname is required")
    private String surname;

    @NotNull(message = "Birthdate is required")
    @Past(message = "Birthdate should be in the past")
    private LocalDate birthdate;

    @Size(min = 6, max = 100, message = "Password should be between 6 and 100 characters")
    @NotBlank(message = "Password should not be empty or blank")
    @NotNull(message = "Password is required")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).*$", message = "Password should contain at least one uppercase letter, one lowercase letter and one digit")
    private String password;

    @Size(min = 6, max = 100, message = "Password should be between 6 and 100 characters")
    @NotBlank(message = "Password should not be empty or blank")
    @NotNull(message = "Password is required")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).*$", message = "Password should contain at least one uppercase letter, one lowercase letter and one digit")
    private String confirmPassword;

    @AssertTrue(message = "You should accept the terms and conditions")
    private boolean acceptTerms;
}