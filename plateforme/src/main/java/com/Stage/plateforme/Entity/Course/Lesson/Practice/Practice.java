package com.Stage.plateforme.Entity.Course.Lesson.Practice;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.Stage.plateforme.Entity.Course.Lesson.Lesson;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Practice {
    @Id
    @GeneratedValue(
        strategy = GenerationType.IDENTITY
    )
    private Long Id;
    private String title;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="lesson_id")
    private Lesson lesson;
    @OneToMany(mappedBy = "practice")
    List<Question> question;
    @OneToMany(mappedBy = "practice")
    List<Student_Practice_Session> sessions=new ArrayList<>();
}
