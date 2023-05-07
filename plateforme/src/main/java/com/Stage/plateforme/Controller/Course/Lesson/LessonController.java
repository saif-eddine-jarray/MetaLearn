package com.Stage.plateforme.Controller.Course.Lesson;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.Stage.plateforme.Entity.Course.Lesson.Lesson;
import com.Stage.plateforme.Service.Implementation.Course.Lesson.LessonService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("lesson")
@CrossOrigin
public class LessonController {
    private final LessonService lessonService;
    @PostMapping("/save/id={id}")
    public ResponseEntity<Lesson> createLesson(@RequestBody Lesson lesson, @PathVariable Long id) {
        return ResponseEntity.ok(lessonService.createLesson(lesson,id));
    }
}
