package com.elearn.app;

import com.elearn.app.config.AppConstants;
import com.elearn.app.entities.User;
import com.elearn.app.entities.Role;
import com.elearn.app.repositories.RoleRepo;
import com.elearn.app.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@SpringBootApplication
public class StartLearnBackApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(StartLearnBackApplication.class, args);
	}

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private RoleRepo roleRepo;

	@Override
	public void run(String... args) throws Exception {

		Role role1 = new Role();
		role1.setRoleName(AppConstants.ROLE_ADMIN);
		role1.setRoleId(UUID.randomUUID().toString());

		Role role2 = new Role();
		role2.setRoleName(AppConstants.ROLE_GUEST);
		role2.setRoleId(UUID.randomUUID().toString());

//		roleRepo.findByRoleName(role1.getRoleName()).ifPresentOrElse(role -> {
//					System.out.println("Role is already present" + role.getRoleName());
//				},
//			()->{
//				roleRepo.save(role1);
//			});
//		roleRepo.findByRoleName(role2.getRoleName()).ifPresentOrElse(role -> {
//					System.out.println("Role is already present" + role.getRoleName());
//				},
//				()->{
//					roleRepo.save(role2);
//				});

//		User user = new User();
//		user.setUserId(UUID.randomUUID().toString());
//		user.setEmail("dhiraj@gmail.com");
//		user.setName("Dhiraj");
//		user.setCreatedAt(new Date());
//		user.setPassword(passwordEncoder.encode("dhiraj"));
//		user.setAbout("This is the main user");
//		user.setActive(true);
//		user.setEmailVerified(true);
//		user.setPhoneNumber("7809878908");
//		user.setProfilePath("image/profile/photu.jpg");
//		user.assignRole(role1);
//		user.assignRole(role2);
//
//		userRepo.findByEmail("dhiraj@gmail.com").ifPresentOrElse(user1->{
//				System.out.println("User is already present in the database : " + user1.getEmail());
//		},()->{
//			userRepo.save(user);
//		});

//		User user1 = new User();
//		user1.setUserId(UUID.randomUUID().toString());
//		user1.setEmail("ram@gmail.com");
//		user1.setName("Ram Singh");
//		user1.setCreatedAt(new Date());
//		user1.setPassword(passwordEncoder.encode("ram"));
//		user1.setAbout("He is a software developer at Google.");
//		user1.setActive(true);
//		user1.setEmailVerified(true);
//		user1.setPhoneNumber("7809878903");
//		user1.setProfilePath("image/profile/ram.jpg");
//		user1.assignRole(role1);
//
//
//		userRepo.findByEmail("ram@gmail.com").ifPresentOrElse(user2->{
//			System.out.println("User is already present in the database : " + user2.getEmail());
//		},()->{
////			userRepo.save(user1);
//		});

	}
}
