package com.ws.webrecipe.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;
import java.util.TreeSet;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Category {
    String label;
    String comment;
    String description;
    String thumbnail;

    Set<Recipe> recipes = new TreeSet<>();
}
