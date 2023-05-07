package com.Stage.plateforme.Repository.Course.Lesson.Practice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.Stage.plateforme.Entity.Course.Lesson.Practice.Question;

public interface QuestionRepository extends JpaRepository<Question, Long>{
    @Query("Select q from Question q where q.id=?1")
    Question findQuestionById(Long question_id);
    
}
