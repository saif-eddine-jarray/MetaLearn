package com.Stage.plateforme.Service.Implementation.Course;

import java.util.List;

import org.springframework.stereotype.Service;

import com.Stage.plateforme.Entity.Course.Course;
import com.Stage.plateforme.Repository.Course.CourseRepository;
import com.Stage.plateforme.Service.Interface.Course.I_CourseService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CourseService implements I_CourseService {
    private final CourseRepository courseRepository;
    @Override
    public Course createCourse(Course course) {
        return courseRepository.save(course);
    }
    
    @Override
    public List<Course> getCourses() {
        return courseRepository.findAll();
    }
    @Override
    public Course getCourse(Long id) {
        return courseRepository.findCourseById(id);
    }
    
}
