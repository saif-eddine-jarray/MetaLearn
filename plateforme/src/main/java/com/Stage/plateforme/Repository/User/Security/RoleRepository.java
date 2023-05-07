package com.Stage.plateforme.Repository.User.Security;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.Stage.plateforme.Entity.User.Security.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);

    @Query("Select r from Role r where r.id=?1")
    Role findRoleById(Long id);
}
