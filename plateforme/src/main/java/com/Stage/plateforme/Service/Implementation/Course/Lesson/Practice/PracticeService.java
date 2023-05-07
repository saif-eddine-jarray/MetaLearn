package com.Stage.plateforme.Service.Implementation.Course.Lesson.Practice;

import org.springframework.stereotype.Service;

import com.Stage.plateforme.Entity.Course.Lesson.Lesson;
import com.Stage.plateforme.Entity.Course.Lesson.Practice.Practice;
import com.Stage.plateforme.Repository.Course.Lesson.LessonRepository;
import com.Stage.plateforme.Repository.Course.Lesson.Practice.PracticeRepository;
import com.Stage.plateforme.Service.Interface.Course.Lesson.Practice.I_PracticeService;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class PracticeService implements I_PracticeService{
    private final LessonRepository lessonRepository;
    private final PracticeRepository practiceRepository;
    @Override
    public Practice createPractice(Practice practice, Long id) {
        Lesson lesson=lessonRepository.findLessonById(id);
        practice.setLesson(lesson);
        return practiceRepository.save(practice);
    }
    
}
