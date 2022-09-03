package com.ws.webrecipe.controller;

import com.ws.webrecipe.model.CategoryType;
import com.ws.webrecipe.model.Recipe;
import com.ws.webrecipe.model.exceptions.RecipeNotFoundException;
import com.ws.webrecipe.service.RecipeService;
import org.apache.commons.lang3.text.WordUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/recipes")
@CrossOrigin(origins = "http://localhost:8081")
public class RecipeController {

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping("/search")
    public Recipe getRecipeInfo(@RequestParam(name = "recipe", required = false) String recipe,
                                @RequestParam(name = "uri", required = false) String uri) throws RecipeNotFoundException {
        if(uri != null) {
            return recipeService.getRecipeInfoWithURI(uri);
        }
        return recipeService.getRecipeInfo(WordUtils.capitalizeFully(recipe));
    }

    @GetMapping("/q")
    public List<Recipe> search(@RequestParam(name = "name") String name,
                               @RequestParam(name = "category") CategoryType category) {
        return recipeService.searchRecipes(name, category);
    }
}
