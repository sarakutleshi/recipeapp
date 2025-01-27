package dev.sarakutleshi.recipeapp.controller.api.v1;


import dev.sarakutleshi.recipeapp.models.Recipe;
import dev.sarakutleshi.recipeapp.services.RecipeService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
// ish dsath -> /api/v1/users/{userId}/recipes
@RequestMapping("/api/v1/recipes")
@RequiredArgsConstructor
public class RecipeRestController {
    private final RecipeService service;


    @GetMapping
    public List<Recipe> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Recipe findById(@PathVariable long id) {
        return service.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Recipe add(@Valid @RequestBody Recipe model) {
        return service.add(model);
    }

    @PutMapping("/{id}")
    public Recipe modify(@PathVariable long id, @Valid @RequestBody Recipe model) {
        return service.modify(id, model);
    }


    @DeleteMapping("/{id}/delete")
    public void delete(@PathVariable long id) {
        service.removeById(id);
    }

    @GetMapping("/{id}/edit")
    public Recipe edit(@PathVariable long id) {
        return service.findById(id);  // Returning recipe for editing
    }

    @GetMapping("/{id}/details")
    public Recipe details(@PathVariable long id) {
        return service.findById(id);
    }

    @GetMapping("/default")
    public Recipe defaultPost() {
        return new Recipe();
    }

}
