package com.ws.webrecipe.repository;

import com.ws.webrecipe.model.exceptions.RecipeNotFoundException;
import com.ws.webrecipe.model.CategoryType;
import com.ws.webrecipe.model.Recipe;

import java.util.List;

public interface RecipeRepository {

    List<Recipe> withName(String name, CategoryType categoryType);

    void addRecipeBaseInfo(String uri, Recipe recipe) throws RecipeNotFoundException;

    void addRecipeCourses(String uri, Recipe recipe);
}
