package com.project;

import com.project.entity.concretes.user.UserRole;
//import com.project.entity.enums.Gender;
import com.project.entity.enums.Role;
//import com.project.entity.enums.RoleType;
import com.project.payload.request.user.UserRequest;
import com.project.repository.user.UserRoleRepository;
import com.project.service.user.UserRoleService;
import com.project.service.user.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;

@SpringBootApplication
//!!! Uygulama run edilir edilmez, Rol tablom bos olacagi icin gerekli rollerin eklenmesini saglamak
// icin bu sinifimizi CommandLineRunner interface inden implement ediyoruz ve icindeki run metodunu
// override etmemiz gerekiyor.
public class RealEstateApplication implements CommandLineRunner {

	private final UserRoleService userRoleService;
	private final UserRoleRepository userRoleRepository;
	private final UserService userService;

	public RealEstateApplication(UserRoleService userRoleService, UserRoleRepository userRoleRepository, UserService userService) {
		this.userRoleService = userRoleService;
		this.userRoleRepository = userRoleRepository;
		this.userService = userService;
	}

	public static void main(String[] args) {
		SpringApplication.run(RealEstateApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//!!! Role tablomu doldurcam
		if(userRoleService.getAllUserRole().isEmpty()) {

			UserRole admin = new UserRole();
			admin.setRoleName("Admin");
			admin.setRole(Role.ADMIN);
			userRoleRepository.save(admin);

/*
			UserRole dean = new UserRole();
			dean.setRole(Role.MANAGER);
			dean.setRoleName("Dean");
			userRoleRepository.save(dean);

			UserRole viceDean = new UserRole();
			viceDean.setRole(Role.CUSTOMER);
			viceDean.setRoleName("Customer");
			userRoleRepository.save(viceDean);
*/


		}

		//!!! Built_IN ADMIN olusturacagiz
		if(userService.countAllAdmins() == 0) {
			UserRequest adminRequest = new UserRequest();
			adminRequest.setUsername("Admin");
			adminRequest.setEmail("admin@admin.com");
			//adminRequest.setSsn("111-11-1111");
			adminRequest.setPassword("12345678");

			adminRequest.setFirstName("Ahmet");
			adminRequest.setLastName("Mehmet");
			adminRequest.setPhone("111-111-1111");

			userService.saveUser(adminRequest, "Admin");
		}

	}
}
