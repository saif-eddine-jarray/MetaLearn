package com.Stage.plateforme.Entity.User.Status;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class tutor{
    @Id
    @GeneratedValue(
        strategy = GenerationType.IDENTITY
    )
    
    private Long id;
    private String firstName;
    private String lastName;
   
}
