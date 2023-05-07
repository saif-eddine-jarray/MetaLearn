package com.Stage.plateforme.Entity.Course.Lesson;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.Stage.plateforme.Entity.Course.Level;
import com.Stage.plateforme.Entity.Course.Lesson.Practice.Practice;
import com.Stage.plateforme.Entity.User.Status.Student;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Lesson {
    @Id
    @GeneratedValue(
        strategy = GenerationType.IDENTITY
    )
    private Long id;
    private String name;
    private Boolean status=false;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="level_id")
    private Level level;
    @OneToMany(mappedBy = "lesson")
    List<Practice> practices;
    @OneToMany(mappedBy = "lesson")
    List<Material> materials;
    @JsonIgnore
    @ManyToMany(mappedBy = "lessons")
    private List<Student> users = new ArrayList<>();
}
