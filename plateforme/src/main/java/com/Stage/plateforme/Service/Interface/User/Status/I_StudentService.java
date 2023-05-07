package com.Stage.plateforme.Service.Interface.User.Status;

import java.util.List;

import com.Stage.plateforme.Entity.Course.Lesson.Lesson;
import com.Stage.plateforme.Entity.User.Status.Student;

public interface I_StudentService {
    List<Student> getStudents();
    Student enrollStudent(Long student_id,Long lesson_id);
    List<Lesson> getEnrolledLesson(Long student_id);
}
