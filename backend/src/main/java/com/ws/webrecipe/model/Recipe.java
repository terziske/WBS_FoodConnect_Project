package com.ws.webrecipe.model;

import com.ws.webrecipe.model.dto.RecipeCourseDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Recipe implements Comparable<Recipe> {
    String URI;
    String name;
    String description;
    String thumbnail;
    String ingredientName;

    List<RecipeCourseDTO> courses = new ArrayList<>();

    @Override
    public int compareTo(Recipe o) {
        if(this.thumbnail != null ) {
            return -1;
        }
        if(this.name != null && o.name != null) {
            return this.name.compareTo(o.name);
        }
        if(this.description != null && o.description != null) {
            return this.description.compareTo(o.description);
        }
        if(this.ingredientName != null && o.ingredientName != null) {
            return this.ingredientName.compareTo(o.ingredientName);
        }
        return 1;
    }
}
