package com.ws.webrecipe.service;

import com.ws.webrecipe.model.exceptions.RecipeNotFoundException;
import com.ws.webrecipe.model.CategoryType;
import com.ws.webrecipe.model.Recipe;

import java.util.List;

public interface RecipeService {

    List<Recipe> searchRecipes(String name, CategoryType categoryType);

    Recipe getRecipeInfo(String name) throws RecipeNotFoundException;

    Recipe getRecipeInfoWithURI(String URI) throws RecipeNotFoundException;
}
