package com.Stage.plateforme.Repository.Course.Lesson.Practice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.Stage.plateforme.Entity.Course.Lesson.Practice.Practice;

public interface PracticeRepository extends JpaRepository<Practice, Long>{
    @Query("Select p from Practice p where p.id=?1")
    Practice findPracticeById(Long id);
    
}
