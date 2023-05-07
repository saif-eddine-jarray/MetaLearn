package com.Stage.plateforme.Entity.User;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.Stage.plateforme.Entity.User.Security.Role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Api_User {
    @Id
    @GeneratedValue(
        strategy = GenerationType.IDENTITY
    )
    private Long Id;
    private String username;
    private String password;
    private boolean status=false;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "ApiUser_Roles",joinColumns = @JoinColumn(name= "apiUser_id"),
    inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles= new ArrayList<>();
}

