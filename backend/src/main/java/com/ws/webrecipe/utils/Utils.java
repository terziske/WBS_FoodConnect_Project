package com.ws.webrecipe.utils;

import com.ws.webrecipe.model.Category;
import com.ws.webrecipe.model.Course;
import com.ws.webrecipe.model.Recipe;
import com.ws.webrecipe.model.dto.RecipeCourseDTO;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;

public class Utils {

    public static void addRecipeBaseInfo(Recipe recipe, QuerySolution qs) {
        recipe.setURI(qs.contains("recipe") && qs.get("recipe").isResource() ?
                qs.getResource("recipe").toString().split("/")[qs.getResource("recipe").toString().split("/").length - 1]
                : null);
        recipe.setName(qs.get("name") != null && qs.get("name").isLiteral() ? qs.get("name").asLiteral().getLexicalForm() : null);
        recipe.setDescription(qs.get("abstract") != null && qs.get("abstract").isLiteral() ? qs.get("abstract").asLiteral().getLexicalForm() : null);
        recipe.setThumbnail(qs.get("thumbnail") != null ? qs.get("thumbnail").toString() : null);
        recipe.setIngredientName(qs.get("ingredientName") != null && qs.get("ingredientName").isLiteral() ? qs.get("ingredientName").asLiteral().getLexicalForm() : null);
    }

    public static void addCategoryBaseInfo(Category category, QuerySolution qs) {
        category.setLabel(qs.get("label") != null ? qs.get("label").asLiteral().getLexicalForm() : null);
        category.setComment(qs.get("comment") != null ? qs.get("comment").asLiteral().getLexicalForm() : null);
        category.setDescription(qs.get("abstract") != null ? qs.get("abstract").asLiteral().getLexicalForm() : null);
        category.setThumbnail(qs.get("thumbnail") != null ? qs.get("thumbnail").toString() : null);
    }

    public static void addCourseBaseInfo(Course course, QuerySolution qs) {
        if (qs.contains("label") && qs.get("label") != null && qs.get("label").isLiteral())
            course.setLabel(qs.get("label").asLiteral().getLexicalForm());
        if (qs.contains("comment") && qs.get("comment") != null && qs.get("comment").isLiteral())
            course.setComment(qs.get("comment").asLiteral().getLexicalForm());
        if (qs.contains("abstract") && qs.get("abstract") != null && qs.get("abstract").isLiteral())
            course.setDescription(qs.get("abstract").asLiteral().getLexicalForm());
    }

    public static void addRecipeCourses(Recipe recipe, ResultSet resultSet) {
        while (resultSet.hasNext()) {
            QuerySolution qs = resultSet.nextSolution();
            String uri = null, label = null, description = null;

            if (qs.contains("course") && qs.get("course") != null)
                uri = qs.getResource("course").toString();
            if (qs.contains("label") && qs.get("label") != null && qs.get("label").isLiteral())
                label = qs.getLiteral("label").getLexicalForm();
            if (qs.contains("abstract") && qs.get("abstract") != null && qs.get("abstract").isLiteral())
                description = qs.getLiteral("abstract").getLexicalForm();

            recipe.getCourses().add(new RecipeCourseDTO(uri, label, description));
        }
    }

    public static void addCategoryRecipes(Category category, ResultSet resultSet) {
        while (resultSet.hasNext()) {
            QuerySolution qs = resultSet.nextSolution();
            Recipe recipe = new Recipe();
            addRecipeBaseInfo(recipe, qs);
            category.getRecipes().add(recipe);
        }
    }
}
