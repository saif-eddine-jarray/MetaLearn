package com.Stage.plateforme.Entity.User.Status;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.JoinColumn;

import com.Stage.plateforme.Entity.Course.Lesson.Lesson;
import com.Stage.plateforme.Entity.Course.Lesson.Practice.Student_Practice_Session;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    @Id
    @GeneratedValue(
        strategy = GenerationType.IDENTITY
    )
    
    private Long id;
    private String firstName;
    private String lastName;
    private String parentName;
    private Integer age;
    
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "Student_Lessons",joinColumns = @JoinColumn(name= "Student_id"),
    inverseJoinColumns = @JoinColumn(name = "Lesson_id"))
    private List<Lesson> lessons= new ArrayList<>();
    @OneToMany(mappedBy = "student")
    List<Student_Practice_Session> sessions=new ArrayList<>();
}
