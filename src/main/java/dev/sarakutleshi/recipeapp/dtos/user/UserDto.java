package dev.sarakutleshi.recipeapp.dtos.user;

import dev.sarakutleshi.recipeapp.models.User;
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

    public UserDto(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.name = user.getName();
        this.surname = user.getSurname();
        this.birthdate = user.getBirthdate();
    }

    public String getUser() {
        return name + " " + surname;
    }

}

