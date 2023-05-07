package com.Stage.plateforme.Repository.User.Status;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.Stage.plateforme.Entity.User.Status.Student;

public interface StudentRepository  extends JpaRepository<Student, Long> {
    @Query("Select s from Student s where s.id=?1")
    Student findStudentById(Long student_id);
    
}
