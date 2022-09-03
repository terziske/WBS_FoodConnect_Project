package com.ws.webrecipe.service.impl;

import com.ws.webrecipe.model.Course;
import com.ws.webrecipe.repository.CourseRepository;
import com.ws.webrecipe.service.CourseService;
import org.springframework.stereotype.Service;
import com.ws.webrecipe.repository.AssetsRepository;

@Service
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;
    private final AssetsRepository assetsRepository;

    public CourseServiceImpl(CourseRepository courseRepository, AssetsRepository assetsRepository) {
        this.courseRepository = courseRepository;
        this.assetsRepository = assetsRepository;
    }

    @Override
    public Course getCourseInfo(String searchCourse) {
        String courseURI = this.assetsRepository.createEntityIdentifier(searchCourse);
        Course course = new Course();
        this.courseRepository.addCourseBaseInfo(courseURI, course);
        this.courseRepository.addCourseRecipes(courseURI, course);
        return course;
    }
}
