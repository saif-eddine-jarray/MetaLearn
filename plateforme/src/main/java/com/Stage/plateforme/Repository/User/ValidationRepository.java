package com.Stage.plateforme.Repository.User;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.Stage.plateforme.Entity.User.Validation;

@Repository
public interface ValidationRepository  extends JpaRepository<Validation, Long>{
    @Query("Select v from Validation v where v.code = ?1 and v.Id=?2 ")
    Validation findUserEmailAndCode(String code, Long id);
    
    @Query("Select v from Validation v where v.Id=?1")
    Validation findUserById(Long integer);

}
