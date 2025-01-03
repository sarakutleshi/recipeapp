package dev.sarakutleshi.recipeapp.dtos;

import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    @PositiveOrZero(message = "User ID must be positive or zero")
    private long id;
    private String username;
    private String email;
    private String name;
    private String surname;
    private LocalDate birthdate;

    public String getFullName() {
        return name + " " + surname;
    }
}

