package com.ws.webrecipe.repository.impl;

import com.ws.webrecipe.assets.JenaAssets;
import com.ws.webrecipe.repository.AssetsRepository;
import org.apache.jena.query.*;
import org.springframework.stereotype.Repository;

@Repository
public class AssetsRepositoryImpl implements AssetsRepository {

    @Override
    public String createEntityIdentifier(String search) {
        search = search.replaceAll(" ", "_");

        String queryString = "prefix dbr: <http://dbpedia.org/resource/> " +
                "prefix dbo: <http://dbpedia.org/ontology/> " +
                "prefix dbp: <http://dbpedia.org/property/>" +
                "SELECT ?uri where { " +
                "dbr:" + search + " dbo:wikiPageRedirects ?uri . " +
                "}";

        Query query = QueryFactory.create(queryString);
        try (QueryExecution queryExecution = QueryExecutionFactory.sparqlService(JenaAssets.SPARQLEndpoint, query)) {
            ResultSet resultSet = queryExecution.execSelect();
            if(resultSet.hasNext()) {
                return resultSet.nextSolution().get("uri").toString();
            }
        } catch (Exception e) {
            System.out.println("============" + e.getLocalizedMessage() + "\n" + e.getCause() + "\n\n");
        }
        return "http://dbpedia.org/resource/" + search;
    }
}
