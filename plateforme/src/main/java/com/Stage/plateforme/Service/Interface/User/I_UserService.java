package com.Stage.plateforme.Service.Interface.User;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Stage.plateforme.Entity.User.User;
import com.Stage.plateforme.Entity.User.Security.Permission;
import com.Stage.plateforme.Entity.User.Security.Role;

public interface I_UserService {
    List<User> getLogins();
    User addNewUser(User user);
    String getEmail(Long id);
    void enableUser(Long id);
    Role saveRole(Role role);
    void addRoleToUser(String username, String roleName);
    User getUserByUserName(String username);
    User getUserByEmail(String email);
    List<User> getUsers();
    void addPermissionToRole(String roleName,Long id);
    void savePermission(Permission api_Permission);
    List<Permission> getPermissionsWithRoles();
    List<Role> getRole();
    User deleteUser(Long id);
    User updateUser(User user,Long id);
    void refreshToken(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
