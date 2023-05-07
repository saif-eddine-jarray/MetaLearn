package com.Stage.plateforme.Controller.Course.Lesson.Practice;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Stage.plateforme.Entity.Course.Lesson.Practice.Question;
import com.Stage.plateforme.Service.Implementation.Course.Lesson.Practice.QuestionService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("question")
@CrossOrigin
public class QuestionController {
    private final QuestionService questionService;
    @PostMapping("/save/id={id}")
    public ResponseEntity<Question> createPractice(@RequestBody Question question, @PathVariable Long id) {
        return ResponseEntity.ok(questionService.createQuestion(question, id));
    }
}
