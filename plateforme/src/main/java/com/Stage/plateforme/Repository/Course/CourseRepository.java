package com.Stage.plateforme.Repository.Course;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.Stage.plateforme.Entity.Course.Course;

public interface CourseRepository extends JpaRepository<Course, Long>{
    @Query("Select c from Course c where c.id=?1")
    Course findCourseById(Long course_id);

}
