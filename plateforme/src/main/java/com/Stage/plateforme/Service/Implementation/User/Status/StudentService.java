package com.Stage.plateforme.Service.Implementation.User.Status;

import java.util.List;

import org.springframework.stereotype.Service;

import com.Stage.plateforme.Entity.Course.Lesson.Lesson;
import com.Stage.plateforme.Entity.User.Status.Student;
import com.Stage.plateforme.Repository.Course.Lesson.LessonRepository;
import com.Stage.plateforme.Repository.User.Status.StudentRepository;
import com.Stage.plateforme.Service.Interface.User.Status.I_StudentService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StudentService implements I_StudentService {
    private final StudentRepository studentRepository;
    private final LessonRepository lessonRepository;
    @Override
    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Student enrollStudent(Long student_id, Long lesson_id) {
        Student student=studentRepository.findStudentById(student_id);
        Lesson lesson=lessonRepository.findLessonById(lesson_id);
        student.getLessons().add(lesson);
        return studentRepository.save(student);
    }

    @Override
    public List<Lesson> getEnrolledLesson(Long student_id) {
        return studentRepository.findStudentById(student_id).getLessons();
    }
    
}
