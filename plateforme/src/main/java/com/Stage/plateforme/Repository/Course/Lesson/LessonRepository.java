package com.Stage.plateforme.Repository.Course.Lesson;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.Stage.plateforme.Entity.Course.Lesson.Lesson;

public interface LessonRepository extends JpaRepository<Lesson, Long>{
    @Query("Select l from Lesson l where l.id=?1")
    Lesson findLessonById(Long lesson_id);
    
}
