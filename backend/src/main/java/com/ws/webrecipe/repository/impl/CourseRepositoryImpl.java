package com.ws.webrecipe.repository.impl;

import com.ws.webrecipe.model.Course;
import com.ws.webrecipe.model.Recipe;
import com.ws.webrecipe.repository.CourseRepository;
import com.ws.webrecipe.utils.Utils;
import org.apache.jena.query.*;
import org.springframework.stereotype.Repository;
import com.ws.webrecipe.assets.JenaAssets;

@Repository
public class CourseRepositoryImpl implements CourseRepository {

    @Override
    public void addCourseBaseInfo(String courseURI, Course course) {
        String queryCourseBaseInfo =
                "prefix dbp: <http://dbpedia.org/property/> " +
                "prefix dbo: <http://dbpedia.org/ontology/> " +
                "prefix dbr: <http://dbpedia.org/resource/> " +
                "prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#> " +
                "SELECT DISTINCT *" +
                " WHERE {" +
                "     OPTIONAL { <" + courseURI + "> rdfs:label ?label . } " +
                "     OPTIONAL { <" + courseURI + "> rdfs:comment ?comment . } " +
                "     OPTIONAL { <" + courseURI + "> dbo:abstract ?abstract . } " +
                "     FILTER( " +
                "        lang(?label) = \"en\" && " +
                "        lang(?comment) = \"en\" && " +
                "        lang(?abstract) = \"en\" " +
                "     )" +
                "}";

        Query query = QueryFactory.create(queryCourseBaseInfo);

        try (QueryExecution queryExecution = QueryExecutionFactory.sparqlService(JenaAssets.SPARQLEndpoint, query)) {
            ResultSet resultSet = queryExecution.execSelect();
            if (resultSet.hasNext()) {
                Utils.addCourseBaseInfo(course, resultSet.nextSolution());
            }
        }
    }

    @Override
    public void addCourseRecipes(String courseURI, Course course) {
        String queryCourseRecipes =
                "prefix dbp: <http://dbpedia.org/property/> " +
                "prefix dbo: <http://dbpedia.org/ontology/> " +
                "prefix dbr: <http://dbpedia.org/resource/> " +
                "prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#> " +
                "prefix rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> " +
                "SELECT DISTINCT *" +
                " WHERE { " +
                "     ?recipe dbo:type  <" + courseURI +">; " +
                "     rdf:type dbo:Food . " +
                "     OPTIONAL { ?recipe dbp:name ?name . } " +
                "     OPTIONAL { ?recipe dbo:abstract ?abstract . } " +
                "     OPTIONAL { ?recipe dbo:thumbnail ?thumbnail . } " +
                "     OPTIONAL { ?recipe dbo:ingredientName ?ingredientName . } " +
                "     FILTER (lang(?abstract) = \"en\") " +
                "}";

        Query query = QueryFactory.create(queryCourseRecipes);

        try (QueryExecution queryExecution = QueryExecutionFactory.sparqlService(JenaAssets.SPARQLEndpoint, query)) {
            ResultSet resultSet = queryExecution.execSelect();
            while (resultSet.hasNext()) {
                Recipe recipe = new Recipe();
                Utils.addRecipeBaseInfo(recipe, resultSet.next());
                course.getRecipes().add(recipe);
            }
        }
    }
}
