package com.Stage.plateforme.Entity.User.Security;

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

import com.Stage.plateforme.Entity.User.Api_User;
import com.Stage.plateforme.Entity.User.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role {

    @Id
    @GeneratedValue(
        strategy = GenerationType.IDENTITY
    )
    private long Id;
    private String name;
    @ManyToMany(fetch= FetchType.EAGER)
    @JoinTable(name="role_permission",
    joinColumns={@JoinColumn(name="role_id")},
    inverseJoinColumns = {@JoinColumn(name="permission_id") }
    )
    private List<Permission> permissions= new ArrayList<>();
    @JsonIgnore
    @ManyToMany(mappedBy = "roles")
    private List<User> users = new ArrayList<>();
    @JsonIgnore
    @ManyToMany(mappedBy = "roles")
    private List<Api_User> apiUsers = new ArrayList<>();
    public Role(String name){
        this.name=name;
    }

}
