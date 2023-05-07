package com.Stage.plateforme.Service.Interface.Course.Lesson.Practice;

import java.util.List;

import com.Stage.plateforme.Entity.Course.Lesson.Practice.Student_Practice_Session;

public interface I_SessionService {
    Student_Practice_Session createSession(Student_Practice_Session session, Long Student_id, Long Practice_id);
    List<Student_Practice_Session> getStudentSessions(Long id);
    Student_Practice_Session getSessionById(Long id);
}
