package com.Stage.plateforme.Controller.Course.Lesson.Practice;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Stage.plateforme.Entity.Course.Lesson.Practice.Student_Practice_Result;
import com.Stage.plateforme.Service.Implementation.Course.Lesson.Practice.ResultService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("result")
@CrossOrigin
public class ResultController {
    private final ResultService resultService;
    
    @PostMapping("/save/session={session_id}&question={question_id}")
    public ResponseEntity<Student_Practice_Result> createResult(
        @RequestBody Student_Practice_Result result,@PathVariable Long session_id,
        @PathVariable Long question_id) {
        return ResponseEntity.ok(resultService.createResult(result, session_id, question_id));
    }
    @GetMapping("/id={id}")
    public ResponseEntity<Student_Practice_Result> getSession(@PathVariable Long id) {
        return ResponseEntity.ok(resultService.getResultById(id));
    }
}
