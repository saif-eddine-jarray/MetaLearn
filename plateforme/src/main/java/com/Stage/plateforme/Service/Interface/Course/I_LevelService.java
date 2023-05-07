package com.Stage.plateforme.Service.Interface.Course;

import java.util.List;

import com.Stage.plateforme.Entity.Course.Level;
import com.Stage.plateforme.Entity.Course.Lesson.Lesson;

public interface I_LevelService {
    Level createLevel(Level level, Long id);
    List<Lesson> getLessons(Long id);
}
