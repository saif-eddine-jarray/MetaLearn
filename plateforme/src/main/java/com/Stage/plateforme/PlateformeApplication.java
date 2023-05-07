package com.Stage.plateforme;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.Stage.plateforme.Entity.User.Security.Permission;
import com.Stage.plateforme.Entity.User.Security.Role;
import com.Stage.plateforme.Service.Implementation.User.UserService;


@SpringBootApplication
public class PlateformeApplication {
	public static void main(String[] args) {
		SpringApplication.run(PlateformeApplication.class, args);
	}
	@Bean
	PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}
	// @Bean
	// CommandLineRunner run(UserService userService){
	// 	return args ->{
	// 		userService.saveRole(new Role( "Admin"));
	// 		userService.saveRole(new Role("Student"));
	// 		userService.saveRole(new Role("Tutor"));
	// 		userService.savePermission(new Permission("/user/**","POST"));
	// 		userService.savePermission(new Permission("/course/**","POST"));
	// 		userService.savePermission(new Permission("/lesson/**","POST"));
	// 		userService.savePermission(new Permission("/level/**","POST"));
	// 		userService.savePermission(new Permission("/practice/**","POST"));
	// 		userService.savePermission(new Permission("/material/**","POST"));
	// 		userService.savePermission(new Permission("/question/**","POST"));
	// 		userService.savePermission(new Permission("/result/**","POST"));
	// 		userService.savePermission(new Permission("/session/**","POST"));
	// 		userService.savePermission(new Permission("/user/**","GET"));
	// 		userService.addPermissionToRole("Admin", Long.valueOf(1));
	// 		userService.addPermissionToRole("Admin",Long.valueOf(2));
	// 		userService.addPermissionToRole("Admin", Long.valueOf(3));
	// 		userService.addPermissionToRole("Admin", Long.valueOf(4));
	// 		userService.addPermissionToRole("Admin", Long.valueOf(5));	
	// 		userService.addPermissionToRole("Admin", Long.valueOf(6));
	// 		userService.addPermissionToRole("Admin", Long.valueOf(7));
	// 		userService.addPermissionToRole("Admin", Long.valueOf(8));	
	// 		userService.addPermissionToRole("Admin", Long.valueOf(9));
	// 		userService.addPermissionToRole("Admin", Long.valueOf(10));
	// 		userService.addPermissionToRole("Tutor", Long.valueOf(1));
	// 		userService.addPermissionToRole("Student", Long.valueOf(1));
	// 		userService.addPermissionToRole("Tutor",Long.valueOf(2));
	// 	};
	// }
}