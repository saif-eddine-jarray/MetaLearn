package com.Stage.plateforme.Service.Implementation.Course.Lesson.Practice;

import java.util.List;

import org.springframework.stereotype.Service;

import com.Stage.plateforme.Entity.Course.Lesson.Practice.Practice;
import com.Stage.plateforme.Entity.Course.Lesson.Practice.Student_Practice_Session;
import com.Stage.plateforme.Entity.User.Status.Student;
import com.Stage.plateforme.Repository.Course.Lesson.Practice.PracticeRepository;
import com.Stage.plateforme.Repository.Course.Lesson.Practice.SessionRepository;
import com.Stage.plateforme.Repository.User.Status.StudentRepository;
import com.Stage.plateforme.Service.Interface.Course.Lesson.Practice.I_SessionService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SessionService implements I_SessionService{
    private final StudentRepository studentRepository;
    private final PracticeRepository practiceRepository;
    private final SessionRepository sessionRepository;
    @Override
    public Student_Practice_Session createSession(Student_Practice_Session session, Long student_id, Long practice_id) {
        Student student=studentRepository.findStudentById(student_id);
        Practice practice=practiceRepository.findPracticeById(practice_id);
        session.setStudent(student);
        session.setPractice(practice);
        return sessionRepository.save(session);
    }
    @Override
    public List<Student_Practice_Session> getStudentSessions(Long id) {
        Student student=studentRepository.findStudentById(id);
        return student.getSessions();
    }
    @Override
    public Student_Practice_Session getSessionById(Long id) {

        return sessionRepository.findSessionById(id);
    }
    
}
