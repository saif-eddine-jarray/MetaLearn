package com.Stage.plateforme.Repository.User.Security;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.Stage.plateforme.Entity.User.Security.Permission;


public interface PermissionRepository extends JpaRepository<Permission, Long>{
    List<Permission> findAll();
    @Query("Select p from Permission p where p.id=?1")
    Permission findBypermissionId(Long id);
    @Query("Select p from Permission p where p.url=?1 and p.method=?2")
    Permission findByUrlandMethod(String url, String method);
}
