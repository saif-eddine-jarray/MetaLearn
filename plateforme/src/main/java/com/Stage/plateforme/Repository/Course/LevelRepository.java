package com.Stage.plateforme.Repository.Course;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.Stage.plateforme.Entity.Course.Level;


public interface LevelRepository extends JpaRepository<Level, Long>{
    @Query("Select l from Level l where l.id=?1")
    Level findLevelById(Long level_id);
    
}
