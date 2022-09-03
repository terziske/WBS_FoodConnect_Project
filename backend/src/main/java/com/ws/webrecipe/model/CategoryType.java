package com.ws.webrecipe.model;

    public enum CategoryType {
    dish("<http://dbpedia.org/resource/Main_course>", "<http://dbpedia.org/ontology/type>"),
    salad("<http://dbpedia.org/resource/Salad>", "<http://dbpedia.org/ontology/type>"),
    dessert("<http://dbpedia.org/resource/Dessert>", "<http://dbpedia.org/ontology/type>"),
    drink("<http://dbpedia.org/resource/Drink>", "<http://dbpedia.org/ontology/type>");

    public final String course;
    public final String property;

    CategoryType(String course, String property) {
        this.course = course;
        this.property = property;
    }
}
