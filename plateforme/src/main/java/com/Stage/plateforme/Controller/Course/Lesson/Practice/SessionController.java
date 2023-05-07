package com.Stage.plateforme.Controller.Course.Lesson.Practice;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Stage.plateforme.Entity.Course.Lesson.Practice.Student_Practice_Session;
import com.Stage.plateforme.Service.Implementation.Course.Lesson.Practice.SessionService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("session")
@CrossOrigin
public class SessionController {
    private final SessionService sessionService;
    @PostMapping("/save/student={student_id}&practice={practice_id}")
    public ResponseEntity<Student_Practice_Session> createSession(@RequestBody Student_Practice_Session session,@PathVariable Long student_id,@PathVariable Long practice_id) {
        return ResponseEntity.ok(sessionService.createSession(session, student_id, practice_id));
    }
    @GetMapping("/Student={id}/sessions")
    public ResponseEntity<List<Student_Practice_Session>> getSessions(@PathVariable Long id) {
        return ResponseEntity.ok(sessionService.getStudentSessions(id));
    }
    @GetMapping("/id={id}")
    public ResponseEntity<Student_Practice_Session> getSession(@PathVariable Long id) {
        return ResponseEntity.ok(sessionService.getSessionById(id));
    }
}
