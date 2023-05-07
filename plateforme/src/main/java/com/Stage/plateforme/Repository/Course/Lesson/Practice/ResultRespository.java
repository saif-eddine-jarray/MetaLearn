package com.Stage.plateforme.Repository.Course.Lesson.Practice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.Stage.plateforme.Entity.Course.Lesson.Practice.Student_Practice_Result;

public interface ResultRespository extends JpaRepository<Student_Practice_Result, Long>{
    @Query("Select r from Student_Practice_Result r where r.id=?1")
    Student_Practice_Result findResultById(Long id);
    
}
