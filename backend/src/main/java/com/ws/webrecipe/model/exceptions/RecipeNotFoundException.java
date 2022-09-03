package com.ws.webrecipe.model.exceptions;

public class RecipeNotFoundException extends Exception{

    public RecipeNotFoundException (String recipeName) {
        super(recipeName + " not found! Check what are you typing!!!");
    }
}
