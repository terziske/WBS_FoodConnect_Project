package com.ws.webrecipe.repository.impl;

import com.ws.webrecipe.assets.JenaAssets;
import com.ws.webrecipe.model.Category;
import com.ws.webrecipe.repository.CategoryRepository;
import com.ws.webrecipe.utils.Utils;
import org.apache.jena.query.*;
import org.springframework.stereotype.Repository;


@Repository
public class CategoryRepositoryImpl implements CategoryRepository {

    @Override
    public void addCategoryBaseInfo(String categoryURI, Category category) {
        String queryCategoryBaseInfo =
                "prefix dbo: <http://dbpedia.org/ontology/> " +
                "prefix dbr: <http://dbpedia.org/resource/> " +
                "prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#> " +
                "SELECT * WHERE { " +
                "             OPTIONAL { dbr:" + categoryURI + " rdfs:label ?label . } " +
                "             OPTIONAL { dbr:" + categoryURI + " rdfs:comment ?comment . } " +
                "             OPTIONAL { dbr:" + categoryURI + " dbo:abstract ?abstract . } " +
                "             OPTIONAL { dbr:" + categoryURI + " dbo:thumbnail ?thumbnail . } " +
                "             FILTER (lang(?abstract) = \"en\" && lang(?label) = \"en\" && lang(?comment) = \"en\")" +
                "}";

        Query query = QueryFactory.create(queryCategoryBaseInfo);

        try (QueryExecution queryExecution = QueryExecutionFactory.sparqlService(JenaAssets.SPARQLEndpoint, query)) {
            ResultSet resultSet = queryExecution.execSelect();
            if (resultSet.hasNext()) {
                Utils.addCategoryBaseInfo(category, resultSet.nextSolution());
            }
        }
    }

    @Override
    public void addCategoryRecipes(String categoryURI, Category category) {
        String queryRecipesOfCategory =
                "prefix dbp: <http://dbpedia.org/property/> " +
                "prefix dbo: <http://dbpedia.org/ontology/> " +
                "prefix dbr: <http://dbpedia.org/resource/> " +
                "prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#> " +
                "prefix rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>" +
                "SELECT DISTINCT * WHERE {{ " +
                "        ?recipe dbo:type dbr:"+ categoryURI +  " . " +
                "        OPTIONAL { ?recipe dbp:name ?name . } " +
                "        OPTIONAL { ?recipe dbo:abstract ?abstract . } " +
                "        OPTIONAL { ?recipe dbo:thumbnail ?thumbnail . } " +
                "        OPTIONAL { ?recipe dbo:ingredientName ?ingredientName . } " +
                "        FILTER (lang(?abstract) = \"en\") " +
                "}" +
                "union {" +
                "        ?recipe dbo:types dbr:"+ categoryURI + " . " +
                "        OPTIONAL { ?recipe dbp:name ?name . } " +
                "        OPTIONAL { ?recipe dbo:abstract ?abstract . } " +
                "        OPTIONAL { ?recipe dbo:thumbnail ?thumbnail . } " +
                "        OPTIONAL { ?recipe dbo:ingredientName ?ingredientName . } " +
                "        FILTER (lang(?abstract) = \"en\") " +
                "}}";

        Query query = QueryFactory.create(queryRecipesOfCategory);
        try (QueryExecution queryExecution = QueryExecutionFactory.sparqlService(JenaAssets.SPARQLEndpoint, query)) {
            ResultSet resultSet = queryExecution.execSelect();
            if (resultSet.hasNext()) {
                Utils.addCategoryRecipes(category, resultSet);
            }
        }
    }
}
