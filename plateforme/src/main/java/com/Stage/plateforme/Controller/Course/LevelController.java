package com.Stage.plateforme.Controller.Course;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Stage.plateforme.Entity.Course.Level;
import com.Stage.plateforme.Entity.Course.Lesson.Lesson;
import com.Stage.plateforme.Service.Implementation.Course.LevelService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("level")
@CrossOrigin
public class LevelController {
    private final LevelService levelService;
    @PostMapping("/save/id={id}")
    public ResponseEntity<Level> createLevel(@RequestBody Level level,@PathVariable Long id) {
        return ResponseEntity.ok(levelService.createLevel(level,id));
    }
    @GetMapping("/lessons/id={id}")
    public ResponseEntity<List<Lesson>> getLessons(@PathVariable Long id){
        return ResponseEntity.ok(levelService.getLessons(id));
    } 
}
