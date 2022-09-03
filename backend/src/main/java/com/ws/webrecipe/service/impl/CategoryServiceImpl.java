package com.ws.webrecipe.service.impl;

import com.ws.webrecipe.model.Category;
import com.ws.webrecipe.repository.CategoryRepository;
import com.ws.webrecipe.service.CategoryService;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category getCategoryInfo(String categoryURI) {
        Category category = new Category();
        this.categoryRepository.addCategoryBaseInfo(categoryURI, category);
        this.categoryRepository.addCategoryRecipes(categoryURI, category);
        return category;
    }
}
