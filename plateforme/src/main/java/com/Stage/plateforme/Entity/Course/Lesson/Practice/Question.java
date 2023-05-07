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
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Question {
    @Id
    @GeneratedValue(
        strategy = GenerationType.IDENTITY
    )
    private Long id;
    private String instruction;
    private Integer time_limit;
    private String question_type;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="practice_id")
    private Practice practice;
    @OneToMany(mappedBy = "question")
    List<Student_Practice_Result> results=new ArrayList<>();
}
