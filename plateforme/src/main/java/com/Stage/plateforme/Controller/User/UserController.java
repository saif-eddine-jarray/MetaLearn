package com.Stage.plateforme.Controller.User;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Stage.plateforme.Entity.User.User;
import com.Stage.plateforme.Entity.User.Security.Role;
import com.Stage.plateforme.Service.Implementation.User.UserService;






@RestController
@RequestMapping("user")
@CrossOrigin
public class UserController {

    private final UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    
    @PostMapping("/save")
    public ResponseEntity<?> registerNewUser(@RequestBody User user) {
        try {
            User newUser=userService.addNewUser(user);            
            return ResponseEntity.ok(newUser);
        } catch (Exception e) {
            return ResponseEntity.ok(e.getMessage());
        }
    }
    @GetMapping("/get/username={username}")
    public ResponseEntity<User> getUser(@PathVariable String username){
        User user = userService.getUserByUserName(username);
        return ResponseEntity.ok(user);
    }
    @GetMapping("/get/email={email}")
    public ResponseEntity<User> getUserByEmail(@PathVariable String email){
        User user = userService.getUserByEmail(email);
        return ResponseEntity.ok(user);
    }
    @GetMapping("/all")
    public ResponseEntity<List<User>> getUsers(){
        List<User> users = userService.getUsers();
        return ResponseEntity.ok(users);
    }
    @GetMapping("/role")
    public ResponseEntity<List<Role>> getRoles(){
        return ResponseEntity.ok(userService.getRole());
    }
    @PostMapping("/add/permission")
    public ResponseEntity<String> addPermission(@RequestParam String roleName, @RequestParam Long id){
        userService.addPermissionToRole(roleName,id);
        return ResponseEntity.ok("added");
    }
    @PostMapping("/role/save")
    public ResponseEntity<Role> saveRole(@RequestBody Role role){
        return ResponseEntity.ok(userService.saveRole(role));
    }
    @PostMapping("/role/username={username}&rolename={roleName}")
    public ResponseEntity<?> addRoleToUser(@PathVariable String username,@PathVariable String roleName){
        userService.addRoleToUser(username,roleName);
        return ResponseEntity.ok("Role added");
    }
    @GetMapping("/refreshToken")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        userService.refreshToken(request, response) ;
    }
    @DeleteMapping("/user/delete/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable Long id){
        return ResponseEntity.ok(userService.deleteUser(id));
    }
    @PutMapping("/user/update/{id}")
    public ResponseEntity<User> updateUser(@RequestBody User user,@PathVariable Long id){
        return ResponseEntity.ok(userService.updateUser(user,id));
    }
}