package com.Stage.plateforme.Service.Implementation.Course;

import java.util.List;

import org.springframework.stereotype.Service;

import com.Stage.plateforme.Entity.Course.Course;
import com.Stage.plateforme.Entity.Course.Level;
import com.Stage.plateforme.Entity.Course.Lesson.Lesson;
import com.Stage.plateforme.Repository.Course.CourseRepository;
import com.Stage.plateforme.Repository.Course.LevelRepository;
import com.Stage.plateforme.Service.Interface.Course.I_LevelService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LevelService implements I_LevelService{
    private final  LevelRepository levelRepository;
    private final CourseRepository courseRepository;
    
    @Override
    public Level createLevel(Level level,Long id ) {
        Course course=courseRepository.findCourseById(id);
        level.setCourse(course);
        return levelRepository.save(level);
        
    }

    @Override
    public List<Lesson> getLessons(Long id) {
        return levelRepository.findLevelById(id).getLessons();
    }
}
