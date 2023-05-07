package com.Stage.plateforme.Controller.Course;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Stage.plateforme.Entity.Course.Course;
import com.Stage.plateforme.Service.Implementation.Course.CourseService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("course")
@CrossOrigin
public class CourseController {
    private final CourseService courseService;
    @PostMapping("/save")
    public Course saveCourse(@RequestBody Course course) {
        return courseService.createCourse(course);
    }
    @GetMapping("/all")
    public List<Course> getCourses() {
        return courseService.getCourses();
    }
    @GetMapping("/id_course={id}")
    public Course getCourse(@PathVariable Long id){
        return courseService.getCourse(id);
    }
}
