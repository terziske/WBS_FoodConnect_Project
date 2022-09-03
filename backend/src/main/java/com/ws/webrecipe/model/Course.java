package com.ws.webrecipe.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Course {
    String label;
    String description;
    String comment;

    Set<Recipe> recipes = new TreeSet<>();
}
