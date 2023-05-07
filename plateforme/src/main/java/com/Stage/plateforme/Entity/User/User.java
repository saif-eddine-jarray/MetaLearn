package com.Stage.plateforme.Entity.User;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.Stage.plateforme.Entity.User.Security.Role;
import com.Stage.plateforme.Entity.User.Status.Student;
import com.Stage.plateforme.Entity.User.Status.tutor;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(
        strategy = GenerationType.IDENTITY
    )
    private Long Id;
    private String username;
    private String password;
    private String email;
    private boolean status=false;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "student_id", referencedColumnName = "id")
    private Student student;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "tutor_id", referencedColumnName = "id")
    private tutor tutor;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "User_Roles",joinColumns = @JoinColumn(name= "user_id"),
    inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles= new ArrayList<>();
    public User(String username,String password,String email){
        this.username=username;
        this.password=password;
        this.email=email;
    }
    
}
