package dev.sarakutleshi.recipeapp.controller.api.v1;

import dev.sarakutleshi.recipeapp.models.Recipe;
import dev.sarakutleshi.recipeapp.services.RecipeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/recipes")
@RequiredArgsConstructor
public class RecipeRestController {
    private final RecipeService service;

    // ✅ Fetch all recipes
    @GetMapping
    public List<Recipe> findAll() {
        return service.findAll();
    }

    // ✅ Fetch a single recipe by ID
    @GetMapping("/{id}")
    public Recipe findById(@PathVariable long id) {
        return service.findById(id);
    }

    // ✅ Add a new recipe
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Recipe add(@Valid @RequestBody Recipe model) {
        return service.add(model);
    }

    // ✅ Update a recipe
    @PutMapping("/{id}")
    public Recipe modify(@PathVariable long id, @Valid @RequestBody Recipe model) {
        if (id != model.getId()) {
            throw new IllegalArgumentException("Recipe ID mismatch");
        }
        return service.modify(id, model);
    }

    // ✅ Delete a recipe (fixed path)
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable long id) {
        service.removeById(id);
    }

    // ✅ Fetch recipe details (same as findById, so this is redundant)
    @GetMapping("/{id}/detail")
    public Recipe details(@PathVariable long id) {
        return service.findById(id);
    }

    // ✅ Return an empty Recipe model for default post
    @GetMapping("/default")
    public Recipe defaultPost() {
        return new Recipe();
    }

    // ✅ Corrected search functionality (Use @RequestParam instead of @PathVariable)
    @GetMapping("/search")
    public List<Recipe> searchByName(@RequestParam("name") String name) {
        return service.searchByName(name);
    }
}
