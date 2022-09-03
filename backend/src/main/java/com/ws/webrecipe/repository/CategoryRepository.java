package com.ws.webrecipe.repository;

import com.ws.webrecipe.model.Category;

public interface CategoryRepository {

    void addCategoryBaseInfo(String categoryURI, Category category);

    void addCategoryRecipes(String sportURI, Category category);
}
