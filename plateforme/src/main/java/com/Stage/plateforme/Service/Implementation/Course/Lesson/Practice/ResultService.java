package com.Stage.plateforme.Service.Implementation.Course.Lesson.Practice;

import org.springframework.stereotype.Service;

import com.Stage.plateforme.Entity.Course.Lesson.Practice.Question;
import com.Stage.plateforme.Entity.Course.Lesson.Practice.Student_Practice_Result;
import com.Stage.plateforme.Entity.Course.Lesson.Practice.Student_Practice_Session;
import com.Stage.plateforme.Repository.Course.Lesson.Practice.QuestionRepository;
import com.Stage.plateforme.Repository.Course.Lesson.Practice.ResultRespository;
import com.Stage.plateforme.Repository.Course.Lesson.Practice.SessionRepository;
import com.Stage.plateforme.Service.Interface.Course.Lesson.Practice.I_ResultService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ResultService implements I_ResultService{
    final private SessionRepository sessionRepository;
    final private QuestionRepository questionRepository;
    final private ResultRespository resultRespository;
    @Override
    public Student_Practice_Result createResult(Student_Practice_Result result, Long session_id, Long question_id) {
        Student_Practice_Session session=sessionRepository.findSessionById(session_id);
        Question question=questionRepository.findQuestionById(question_id);
        result.setSession(session); 
        result.setQuestion(question);
        return resultRespository.save(result);
    }
    @Override
    public Student_Practice_Result getResultById(Long id) {
        
        return resultRespository.findResultById(id);
    }
    
}
