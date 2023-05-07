package com.Stage.plateforme.Service.Implementation.Course.Lesson.Practice;

import org.springframework.stereotype.Service;

import com.Stage.plateforme.Entity.Course.Lesson.Practice.Practice;
import com.Stage.plateforme.Entity.Course.Lesson.Practice.Question;
import com.Stage.plateforme.Repository.Course.Lesson.Practice.PracticeRepository;
import com.Stage.plateforme.Repository.Course.Lesson.Practice.QuestionRepository;
import com.Stage.plateforme.Service.Interface.Course.Lesson.Practice.I_QuestionService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QuestionService implements I_QuestionService{
    private final PracticeRepository practiceRepository;
    private final QuestionRepository questionRepository;
    @Override
    public Question createQuestion(Question question, Long id) {
        Practice practice=practiceRepository.findPracticeById(id);
        question.setPractice(practice);
        return questionRepository.save(question);
    }
    
}
