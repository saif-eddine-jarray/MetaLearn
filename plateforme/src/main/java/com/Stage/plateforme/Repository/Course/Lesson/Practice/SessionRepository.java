package com.Stage.plateforme.Repository.Course.Lesson.Practice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.Stage.plateforme.Entity.Course.Lesson.Practice.Student_Practice_Session;

public interface SessionRepository extends JpaRepository<Student_Practice_Session, Long> {
    @Query("Select s from Student_Practice_Session s where s.id=?1")
    Student_Practice_Session findSessionById(Long session_id);
    
}
