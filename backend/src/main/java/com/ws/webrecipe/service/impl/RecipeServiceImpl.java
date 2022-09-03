package com.ws.webrecipe.service.impl;

import com.ws.webrecipe.model.CategoryType;
import com.ws.webrecipe.model.Recipe;
import com.ws.webrecipe.model.exceptions.RecipeNotFoundException;
import com.ws.webrecipe.repository.AssetsRepository;
import com.ws.webrecipe.repository.RecipeRepository;
import com.ws.webrecipe.service.RecipeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepository;
    private final AssetsRepository assetsRepository;

    public RecipeServiceImpl(RecipeRepository recipeRepository, AssetsRepository assetsRepository) {
        this.recipeRepository = recipeRepository;
        this.assetsRepository = assetsRepository;
    }

    @Override
    public List<Recipe> searchRecipes(String name, CategoryType categoryType) {
        return this.recipeRepository.withName(name, categoryType);
    }

    @Override
    public Recipe getRecipeInfo(String name) throws RecipeNotFoundException {
        String uri = this.assetsRepository.createEntityIdentifier(name);
        Recipe recipe = new Recipe();
        this.recipeRepository.addRecipeBaseInfo(uri, recipe);
        this.recipeRepository.addRecipeCourses(uri, recipe);
        recipe.setURI(uri);
        return recipe;
    }

    @Override
    public Recipe getRecipeInfoWithURI(String uri) throws RecipeNotFoundException {
        Recipe recipe = new Recipe();
        String tempUri = uri;
        uri = "http://dbpedia.org/resource/" + uri;
        this.recipeRepository.addRecipeBaseInfo(uri, recipe);
        this.recipeRepository.addRecipeCourses(uri, recipe);
        recipe.setURI(tempUri);
        return recipe;
    }
}
