package com.ws.webrecipe.controller;

import com.ws.webrecipe.model.Course;
import com.ws.webrecipe.service.CourseService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/courses")
@CrossOrigin(origins = "http://localhost:8081")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("/search")
    public Course getCourseInfo(@RequestParam("course") String searchCourse) {
        return this.courseService.getCourseInfo(searchCourse);
    }
}
