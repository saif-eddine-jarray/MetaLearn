package com.Stage.plateforme.Controller.User.Status;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Stage.plateforme.Entity.Course.Lesson.Lesson;
import com.Stage.plateforme.Entity.User.Status.Student;
import com.Stage.plateforme.Service.Implementation.User.Status.StudentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("student")
@CrossOrigin
public class StudentController {
    private final StudentService studentService;

    @GetMapping("/all")
    public ResponseEntity<List<Student>> getall(){
        return ResponseEntity.ok(studentService.getStudents());
    }
    @GetMapping("{id}/lessons")
    public ResponseEntity<List<Lesson>> getLessons(@PathVariable Long id){
        return ResponseEntity.ok(studentService.getEnrolledLesson(id));
    }
    @PutMapping("student={s_id}&lesson={l_id}")
    public ResponseEntity<Student> enrollStudent(@PathVariable Long s_id,@PathVariable Long l_id) {
        return ResponseEntity.ok(studentService.enrollStudent(s_id, l_id)); 
    }
}
