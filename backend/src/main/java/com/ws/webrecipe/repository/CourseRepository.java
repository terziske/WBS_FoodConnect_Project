package com.ws.webrecipe.repository;

import com.ws.webrecipe.model.Course;

public interface CourseRepository {

    void addCourseBaseInfo(String courseURI, Course course);

    void addCourseRecipes(String courseURI, Course course);
}
