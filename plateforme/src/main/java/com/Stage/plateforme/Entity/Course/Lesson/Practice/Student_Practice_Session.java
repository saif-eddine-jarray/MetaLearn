package com.Stage.plateforme.Entity.Course.Lesson.Practice;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.Stage.plateforme.Entity.User.Status.Student;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student_Practice_Session {
    @Id
    @GeneratedValue(
        strategy = GenerationType.IDENTITY
    )
    Long id;
    LocalDateTime session_date;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "Student_id")
    Student student;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "Practice_id")
    Practice practice;
    @OneToMany(mappedBy = "session")
    List<Student_Practice_Result> results=new ArrayList<>();
}
