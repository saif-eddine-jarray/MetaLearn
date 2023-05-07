package com.Stage.plateforme.Repository.User;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.Stage.plateforme.Entity.User.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
    User findByUsername(String username);
    @Query("Select u from User u where u.Id=?1")
    Optional<User> FindUserById(Long Id);

    @Query("Select u from User u where u.email=?1")
    User findEmail(String email);

    @Query("Select u from User u where u.Id=?1")
    User findUserById(Long id);
    
    Optional<User> findUserByEmail(String email);

    @Query("Select u from User u where u.email=?1")
    User FindUserByEmail(String email);

    Optional<User> findUserByUsername(String username);
    
}
