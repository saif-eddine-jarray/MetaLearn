package com.Stage.plateforme.Service.Implementation.Course.Lesson;

import org.springframework.stereotype.Service;

import com.Stage.plateforme.Entity.Course.Lesson.Lesson;
import com.Stage.plateforme.Entity.Course.Lesson.Material;
import com.Stage.plateforme.Repository.Course.Lesson.LessonRepository;
import com.Stage.plateforme.Repository.Course.Lesson.MaterialRepository;
import com.Stage.plateforme.Service.Interface.Course.Lesson.I_MaterialService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MaterialService implements I_MaterialService{
    private final LessonRepository lessonRepository;
    private final MaterialRepository materialRepository;
    @Override
    public Material createMatreial(Material material, Long id) {
        Lesson lesson=lessonRepository.findLessonById(id);
        material.setLesson(lesson);
        return materialRepository.save(material);
    }
    
}
