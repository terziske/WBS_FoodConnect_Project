package com.ws.webrecipe.repository.impl;

import com.ws.webrecipe.model.exceptions.RecipeNotFoundException;
import com.ws.webrecipe.repository.RecipeRepository;
import com.ws.webrecipe.assets.JenaAssets;
import com.ws.webrecipe.model.CategoryType;
import com.ws.webrecipe.model.Recipe;
import com.ws.webrecipe.utils.Utils;
import org.apache.jena.query.*;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class RecipeRepositoryImpl implements RecipeRepository {

    @Override
    public List<Recipe> withName(String name, CategoryType categoryType) {
        String queryString =
                "prefix dbp: <http://dbpedia.org/property/> " +
                "prefix dbo: <http://dbpedia.org/ontology/> " +
                "prefix dbr: <http://dbpedia.org/resource/> " +
                "prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#> " +
                "prefix rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> " +
                "SELECT * WHERE { " +
                "             ?recipe " + categoryType.property + " " + categoryType.course +
                "             ;dbp:name ?name." +
                "             OPTIONAL { ?recipe dbo:abstract ?abstract. } " +
                "             OPTIONAL { ?recipe dbo:thumbnail ?thumbnail. } " +
                "             OPTIONAL { ?recipe dbo:ingredientName ?ingredientName. } " +
                "             FILTER (lang(?abstract) = \"en\") " +
                "             FILTER regex(lcase(str(?name)), \".*" + name.toLowerCase() + ".*\")" +
                "}";

        Query query = QueryFactory.create(queryString);

        List<Recipe> recipes = new ArrayList<>();

        try (QueryExecution queryExecution = QueryExecutionFactory.sparqlService(JenaAssets.SPARQLEndpoint, query)) {
            ResultSet resultSet = queryExecution.execSelect();
            while (resultSet.hasNext()) {
                Recipe recipe = new Recipe();
                Utils.addRecipeBaseInfo(recipe, resultSet.next());
                recipes.add(recipe);
            }
        }
        return recipes;
    }

    @Override
    public void addRecipeBaseInfo(String uri, Recipe recipe) throws RecipeNotFoundException {
        String queryRecipeBaseInfo =
                "prefix dbp: <http://dbpedia.org/property/> " +
                "prefix dbo: <http://dbpedia.org/ontology/> " +
                "prefix dbr: <http://dbpedia.org/resource/> " +
                "prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#> " +
                "SELECT * WHERE { " +
                "             OPTIONAL { <" + uri + "> dbp:name ?name . } " +
                "             OPTIONAL { <" + uri + "> dbo:abstract ?abstract . } " +
                "             OPTIONAL { <" + uri + "> dbo:thumbnail ?thumbnail . } " +
                "             OPTIONAL { <" + uri + "> dbo:ingredientName ?ingredientName . } " +
                "             FILTER (lang(?abstract) = \"en\") " +
                "}";

        Query query = QueryFactory.create(queryRecipeBaseInfo);

        try (QueryExecution queryExecution = QueryExecutionFactory.sparqlService(JenaAssets.SPARQLEndpoint, query)) {
            ResultSet resultSet = queryExecution.execSelect();
            if (resultSet.hasNext()) {
                Utils.addRecipeBaseInfo(recipe, resultSet.next());
            } else throw new RecipeNotFoundException(uri.split("/")[uri.split("/").length - 1]);
        }
    }

    @Override
    public void addRecipeCourses(String uri, Recipe recipe) {
        String queryRecipeCourse =
                "prefix dbp: <http://dbpedia.org/property/> " +
                "prefix dbo: <http://dbpedia.org/ontology/> " +
                "prefix dbr: <http://dbpedia.org/resource/> " +
                "prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#> " +
                "SELECT *" +
                "WHERE { " +
                "    <" + uri + "> dbo:type ?course . " +
                "    ?course rdfs:label ?label; " +
                "    dbo:abstract ?abstract . " +
                "    OPTIONAL { <" + uri + "> dbo:ingredientName ?ingredientName . } " +
                "    FILTER ( " +
                "        lang(?abstract) = \"en\" && " +
                "        lang(?label) = \"en\" " +
                "    )" +
                "}";

        Query query = QueryFactory.create(queryRecipeCourse);

        try (QueryExecution queryExecution = QueryExecutionFactory.sparqlService(JenaAssets.SPARQLEndpoint, query)) {
            ResultSet resultSet = queryExecution.execSelect();
            Utils.addRecipeCourses(recipe, resultSet);
        }
    }
}

