package com.Stage.plateforme.Repository.Course.Lesson;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Stage.plateforme.Entity.Course.Lesson.Material;

public interface MaterialRepository extends JpaRepository<Material, Long>{
    
}
