package dev.sarakutleshi.recipeapp.dtos.user;

import dev.sarakutleshi.recipeapp.models.Role;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserListingDto {
    private Long id;
    private String name;
    private String surname;
    private String username;
    private String email;
    private Role role;
}