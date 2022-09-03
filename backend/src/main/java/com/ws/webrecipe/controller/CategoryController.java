package com.ws.webrecipe.controller;

import com.ws.webrecipe.model.Category;
import com.ws.webrecipe.service.CategoryService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/categories")
@CrossOrigin(origins = "http://localhost:8081")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/search")
    public Category getCategoryInfo(@RequestParam("category") String categoryURI) {
        return this.categoryService.getCategoryInfo(categoryURI);
    }
}
