package com.Stage.plateforme.Entity.User.Security;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import javax.persistence.ManyToMany;


import com.fasterxml.jackson.annotation.JsonIgnore;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Permission {
    @javax.persistence.Id
    @GeneratedValue(
        strategy = GenerationType.IDENTITY
    )
    private long Id;
    private String url;
    private String method;
    @JsonIgnore
    @ManyToMany(mappedBy = "permissions",fetch = FetchType.EAGER)
    private List<Role> roles= new ArrayList<>();

    public String[] getRoleNames(){

        List<String> rolesNames= new ArrayList<>();
        this.roles.forEach(role -> {
            rolesNames.add(role.getName());
        });
        String[] role=rolesNames.toArray( new String[0]);
        return role;
    }
    public Permission(String url, String method){
        this.url=url;
        this.method=method;
    }
}
