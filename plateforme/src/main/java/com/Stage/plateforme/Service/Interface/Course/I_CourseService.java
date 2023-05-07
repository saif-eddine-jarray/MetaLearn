package com.Stage.plateforme.Service.Interface.Course;

import java.util.List;

import com.Stage.plateforme.Entity.Course.Course;

public interface I_CourseService {
    Course createCourse(Course course);
    List<Course> getCourses();
    Course getCourse(Long id);
}
