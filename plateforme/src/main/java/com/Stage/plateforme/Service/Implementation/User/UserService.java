package com.Stage.plateforme.Service.Implementation.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.Stage.plateforme.Entity.User.User;
import com.Stage.plateforme.Entity.User.Security.Permission;
import com.Stage.plateforme.Entity.User.Security.Role;
import com.Stage.plateforme.Entity.User.Status.Student;
import com.Stage.plateforme.Entity.User.Status.tutor;
import com.Stage.plateforme.Repository.User.UserRepository;
import com.Stage.plateforme.Repository.User.Security.PermissionRepository;
import com.Stage.plateforme.Repository.User.Security.RoleRepository;
import com.Stage.plateforme.Repository.User.Status.StudentRepository;
import com.Stage.plateforme.Repository.User.Status.TutorRepository;
import com.Stage.plateforme.Service.Interface.User.I_UserService;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Service
@RequiredArgsConstructor
@Slf4j
public class UserService implements I_UserService,UserDetailsService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PermissionRepository permissionRepository;
    private final PasswordEncoder passwordEncoder;
    private final StudentRepository studentRepository;
    private final TutorRepository tutorRepository;

    public List<User> getLogins() {
		return userRepository.findAll();
	}

    public User addNewUser(User user) {
        Optional<User> userOptional = userRepository.findUserByEmail(user.getEmail());
        tutor tutor=user.getTutor();
        Student student=user.getStudent();
        if(userOptional.isPresent()){
            throw new UsernameNotFoundException("Mail already exist");
        }
        Optional<User> userOptional1 = userRepository.findUserByUsername(user.getUsername());
        if(userOptional1.isPresent()){
            throw new UsernameNotFoundException("Username already exist");
        }
        if(user.getStudent()!=null){
            student=studentRepository.save(student);
            user.getRoles().add(roleRepository.findRoleById(Long.valueOf(2))); 
        }
        if(user.getTutor()!=null){
            tutor=tutorRepository.save(user.getTutor());
            user.getRoles().add(roleRepository.findRoleById(Long.valueOf(3))); 
        }
       
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setStudent(student);
        user.setTutor(tutor);
        for(Role role : user.getRoles()){
            Role role2=roleRepository.findRoleById(role.getId());
            user.getRoles().remove(role);
            user.getRoles().add(role2);
        }
        return userRepository.save(user);
    }

    public void enableUser(Long id) {
        Optional<User> userOptional = userRepository.FindUserById(id);
        if (userOptional.isPresent()!=true) {
            throw new IllegalStateException("email doesn't exist");
        }
        User user= userRepository.findUserById(id);
        user.setStatus(true);
        userRepository.save(user);
    }
    public String getEmail(Long id) {
        User user=userRepository.findUserById(id);
        return user.getEmail();
    }
    @Override
    public Role saveRole(Role role) {
        Role newRole = new Role();
        if(roleRepository.findByName(role.getName())!=null){
            newRole.setId(roleRepository.findByName(role.getName()).getId());
        }
        newRole.setName(role.getName());
        for(Permission permission : role.getPermissions()){
                savePermission(permission);
            newRole.getPermissions().add(permission);
        }
        roleRepository.save(newRole);
        return newRole;
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        User user = userRepository.findByUsername(username);
        Role role = roleRepository.findByName(roleName);
        user.getRoles().add(role);
        userRepository.save(user);
    }

    @Override
    public User getUserByUserName(String username) {
        return userRepository.findByUsername(username);
    }
    @Override
    public User getUserByEmail(String email) {
        return userRepository.findEmail(email);
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if(user==null){
            throw new UsernameNotFoundException("username name not found");
        }
        Collection<SimpleGrantedAuthority> authorities =new ArrayList<>();
        user.getRoles().forEach(role -> {authorities.add(new SimpleGrantedAuthority(role.getName()));});
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }
    @Override
    public void addPermissionToRole(String roleName,Long id){
        Role role = roleRepository.findByName(roleName);
        Permission permission = permissionRepository.findBypermissionId(id);
        role.getPermissions().add(permission);
        roleRepository.save(role);
    }
    @Override
    public void savePermission(Permission permission) {
        if(permissionRepository.findByUrlandMethod(permission.getUrl(),permission.getMethod())!=null){
            permission.setId(permissionRepository.findByUrlandMethod(permission.getUrl(),permission.getMethod()).getId());
        }else{
            permissionRepository.save(permission);
        }
        
    }

    @Override
    public List<Permission> getPermissionsWithRoles() {
        return permissionRepository.findAll();
    }

    @Override
    public List<Role> getRole() {
        return roleRepository.findAll();
    }

    @Override
    public User deleteUser(Long id) {
        User user=userRepository.findUserById(id);
        userRepository.delete(user);
        return user;
        
    }

    @Override
    public User updateUser(User user,Long id) {
        Student student=userRepository.findUserById(id).getStudent();
        tutor tutor=userRepository.findUserById(id).getTutor();
        student=updateStudent(user.getStudent(),student);
        tutor=updateTutor(user.getTutor(),tutor);
        user.setStudent(student);
        user.setTutor(tutor);
        user.setId(id);
        return userRepository.save(user);
    }

    private tutor updateTutor(tutor t, tutor tutor) {
        if(tutor!=null){
        if(t!=null){
            t.setId(t.getId());
            return tutorRepository.save(t);
        }else{
            return tutorRepository.save(tutor);
        }
        }else{
            return null;
        }
    }

    private Student updateStudent(Student s,Student student) {
        if(student!=null){
            if(s!=null){
                s.setId(s.getId());
                return studentRepository.save(s);
            }else{
                return studentRepository.save(student);
            }
        }else{
            return null;
        }
    }

    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")){
            try{
                String refreshToken = authorizationHeader.substring("Bearer ".length());
                Algorithm algorithm=Algorithm.HMAC256("Stage".getBytes());
                JWTVerifier verifier=JWT.require(algorithm).build();
                DecodedJWT decodedJWT = verifier.verify(refreshToken);
                String username=decodedJWT.getSubject();
                User user=getUserByUserName(username);
                String accessToken = JWT.create().withSubject(user.getUsername())
                    .withExpiresAt(new Date(System.currentTimeMillis()+30*60*1000))
                    .withIssuer(request.getRequestURL().toString())
                    .withClaim("roles", user.getRoles().stream().map(Role::getName).collect(Collectors.toList()))
                    .sign(algorithm);
                response.setHeader("accessToken", accessToken);
                response.setHeader("refreshToken", refreshToken);
            }catch(Exception exception){
                log.error("error logging in {}", exception.getMessage());
                response.setHeader("error", exception.getMessage());
                Map<String, String> error= new HashMap<>();
                error.put("error", exception.getMessage());
                response.setContentType("application/json");
                new ObjectMapper().writeValue(response.getOutputStream(), error);
            }
        }else{
            throw new RuntimeException("Refresh Token is missing");
        }
    }
        
}        

