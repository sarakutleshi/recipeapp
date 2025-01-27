package dev.sarakutleshi.recipeapp.controller;

import dev.sarakutleshi.recipeapp.dtos.UserDto;
import dev.sarakutleshi.recipeapp.helpers.FileHelper;
import dev.sarakutleshi.recipeapp.models.Recipe;
import dev.sarakutleshi.recipeapp.services.RecipeService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;

@Controller
public class RecipeController {

    private final RecipeService recipeService;
    private final FileHelper fileHelper;

    public RecipeController(RecipeService recipeService, FileHelper fileHelper) {

        this.recipeService = recipeService;
        this.fileHelper = fileHelper;
    }

    @GetMapping("/index")
    public String index(Model model, @RequestParam(required = false) String error) {

        if (error != null) {
            if (error.equals("SUCCESS")) {
                model.addAttribute("success", "Recipe added successfully!");
            }
        }

        var recipes = recipeService.findAll();
        model.addAttribute("recipes", recipes);
        return "recipes/index";
    }

    @GetMapping("/new")
    public String newRecipe(Model model) {
        model.addAttribute("recipe", new Recipe());
        return "recipes/new";
    }

    @PostMapping("/new")
    public String newRecipe(@Valid @ModelAttribute Recipe recipe
            , BindingResult bindingResult
            , RedirectAttributes redirectAttributes
            , @RequestParam("documentFile") MultipartFile documentFile
            , HttpServletRequest request) {

        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(System.out::println);
            return "recipes/new";
        }

        System.out.println("Document file: " + documentFile.getOriginalFilename());

        if (!documentFile.isEmpty()) {
            try {
                String newFilename = fileHelper.uploadFile("target/classes/static/images/recipes/",
                        documentFile.getOriginalFilename(), documentFile.getBytes());

                if (newFilename != null) {
                    recipe.setImageUrl("/images/recipes/" + newFilename);
                }
            } catch (Exception ex) {
                System.out.println("Error: " + ex.getMessage());
            }
        }

        HttpSession session = request.getSession(false);
        if (session == null) {
            redirectAttributes.addFlashAttribute("error", "Session expired, please log in again.");
            return "redirect:/login";
        }


        UserDto userDto = (UserDto) session.getAttribute("user");
        recipeService.add(recipe);
        redirectAttributes.addAttribute("errorId", "SUCCESS");
        redirectAttributes.addFlashAttribute("success", "Recipe Successfully registererd!");
        return "redirect:/index";
    }


    @GetMapping("/{id}/edit")
    public String editRecipe(Model model, @PathVariable Long id) {
        var recipe = recipeService.findById(id);
        model.addAttribute("recipe", recipe);
        return "recipes/edit";
    }


    @PostMapping("/{id}/edit")
    public String editRecipe(@Valid @ModelAttribute Recipe recipe
            , BindingResult bindingResult
            , @PathVariable Long id
            , RedirectAttributes redirectAttributes
            , @RequestParam("documentFile") MultipartFile documentFile,
                             @SessionAttribute("user") UserDto userDto) {

        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(System.out::println);
            return "recipes/edit";
        }


        if (recipe.getId() != id) {
            redirectAttributes.addAttribute("errorId", "CS404");
            redirectAttributes.addFlashAttribute("error", "Recipe Id mismatch!");
            return "redirect:/index";
        }

        recipe.setModifiedAt(LocalDateTime.now());

        if (!documentFile.isEmpty()) {
            try {
                String newFilename = fileHelper.uploadFile("target/classes/static/images/recipes/",
                        documentFile.getOriginalFilename(), documentFile.getBytes());

                if (newFilename != null) {
                    recipe.setImageUrl("/images/recipes/" + newFilename);
                }
            } catch (Exception ex) {
                System.out.println("Error: " + ex.getMessage());
            }
        }
        recipeService.modify(id, recipe);
        return "redirect:/index";
    }
    @GetMapping("{id}/details")
    public String detailCustomer(Model model, @PathVariable Long id) {
        var recipe = recipeService.findById(id);
        model.addAttribute("recipe", recipe);
        return "recipes/detail";
    }

    @GetMapping("{id}/delete")
    public String deleteCustomer(Model model, @PathVariable Long id) {
        var recipe = recipeService.findById(id);
        model.addAttribute("recipe", recipe);
        return "recipes/delete";  // Render delete confirmation page
    }

    @PostMapping("{id}/delete")
    public String deleteCustomer(@PathVariable Long id) {
        recipeService.deleteById(id);
        return "redirect:/index";  // Redirect to the index page after deletion
    }

}
