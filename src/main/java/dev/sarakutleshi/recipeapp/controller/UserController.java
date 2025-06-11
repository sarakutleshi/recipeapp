package dev.sarakutleshi.recipeapp.controller;

import dev.sarakutleshi.recipeapp.dtos.user.CreateUserRequestDto;
import dev.sarakutleshi.recipeapp.dtos.user.UpdateUserRequestDto;
import dev.sarakutleshi.recipeapp.dtos.user.UserDto;
import dev.sarakutleshi.recipeapp.dtos.user.UserListingDto;
import dev.sarakutleshi.recipeapp.models.User;
import dev.sarakutleshi.recipeapp.services.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping("")
    public ResponseEntity<List<UserListingDto>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> findById(@PathVariable Long id) {
        UserDto userDetails = service.findById(id);
        return ResponseEntity.ok(userDetails);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UpdateUserRequestDto> modify(@Valid @RequestBody UpdateUserRequestDto dto, @PathVariable Long id) {
        return ResponseEntity.ok(service.modify(dto, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.removeById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/default")
    public CreateUserRequestDto defaultDriver() {
        return new CreateUserRequestDto();
    }


}

