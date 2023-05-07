package com.Stage.plateforme.Controller.Course.Lesson.Practice;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Stage.plateforme.Entity.Course.Lesson.Practice.Practice;
import com.Stage.plateforme.Service.Implementation.Course.Lesson.Practice.PracticeService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("practice")
@CrossOrigin
public class PracticeContoller {
    private final PracticeService practiceService;
    @PostMapping("/save/id={id}")
    public ResponseEntity<Practice> createPractice(@RequestBody Practice practice, @PathVariable Long id) {
        return ResponseEntity.ok(practiceService.createPractice(practice, id));
    }
}
