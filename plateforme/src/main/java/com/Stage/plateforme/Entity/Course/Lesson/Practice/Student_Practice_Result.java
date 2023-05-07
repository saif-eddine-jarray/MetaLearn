package com.Stage.plateforme.Entity.Course.Lesson.Practice;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student_Practice_Result {
    @Id
    @GeneratedValue(
        strategy = GenerationType.IDENTITY
    )
    Long id;
    Integer execution_time;
    Integer execution_result;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "practice_session_id")
    Student_Practice_Session session;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "question_id")
    Question question;
}