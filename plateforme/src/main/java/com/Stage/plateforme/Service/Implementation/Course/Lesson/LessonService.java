package com.Stage.plateforme.Service.Implementation.Course.Lesson;

import org.springframework.stereotype.Service;

import com.Stage.plateforme.Entity.Course.Level;
import com.Stage.plateforme.Entity.Course.Lesson.Lesson;
import com.Stage.plateforme.Repository.Course.LevelRepository;
import com.Stage.plateforme.Repository.Course.Lesson.LessonRepository;
import com.Stage.plateforme.Service.Interface.Course.Lesson.I_LessonService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LessonService implements I_LessonService {
    private final LessonRepository lessonRepository;
    private final LevelRepository levelRepository;

    @Override
    public Lesson createLesson(Lesson lesson,Long id) {
        Level level=levelRepository.findLevelById(id);
        lesson.setLevel(level);
        return lessonRepository.save(lesson);
    }

}
