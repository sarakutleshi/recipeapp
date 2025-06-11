package dev.sarakutleshi.recipeapp.controller.api.v1;

import dev.sarakutleshi.recipeapp.models.Recipe;
import dev.sarakutleshi.recipeapp.services.RecipeService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
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
        if (id != model.getId()) {
            throw new IllegalArgumentException("Recipe ID mismatch");
        }
        return service.modify(id, model);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable long id) {
        service.removeById(id);
    }

    @GetMapping("/{id}/detail")
    public Recipe details(@PathVariable long id) {
        return service.findById(id);
    }


    @GetMapping("/default")
    public Recipe defaultPost() {
        return new Recipe();
    }

    @PostMapping("/new")
    @ResponseBody
    public ResponseEntity<?> newRecipeJson(
            @Valid @ModelAttribute Recipe recipe,
            BindingResult bindingResult,
            @RequestParam("documentFile") MultipartFile documentFile,
            HttpServletRequest request) {

        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        }

        service.add(recipe);

        return ResponseEntity.ok(Map.of("message", "Recipe submitted successfully"));
    }

    @GetMapping("/details/{id}")
    public String details(Model model, @PathVariable Long id) {
        var recipe = service.findById(id);
        model.addAttribute("recipe", recipe);
        return "recipes/detail";
    }

    @GetMapping("/search")
    public List<Recipe> searchByName(@RequestParam("name") String name) {
        return service.searchByName(name);
    }
}
