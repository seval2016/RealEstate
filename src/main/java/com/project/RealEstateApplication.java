package com.project;

import com.project.entity.concretes.user.UserRole;
import com.project.entity.enums.Role;
import com.project.payload.request.user.UserRequest;
import com.project.service.user.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;

@SpringBootApplication
public class RealEstateApplication {

	private final UserService userService;

	public RealEstateApplication(UserService userService) {
		this.userService = userService;
	}

	public static void main(String[] args) {
		SpringApplication.run(RealEstateApplication.class, args);}

		public void run(String... args) throws Exception {

			if(userService.countAllAdmins() == 0) {
				UserRequest adminRequest = new UserRequest();
				adminRequest.setUsername("Admin");
				/*adminRequest.setEmail("admin@admin.com");
				adminRequest.setSsn("111-11-1111");*/
				adminRequest.setPassword("12345678");
				/*adminRequest.setName("Ahmet");
				adminRequest.setSurname("Ahmet");
				adminRequest.setPhoneNumber("111-111-1111");
				adminRequest.setGender(Gender.MALE);
				adminRequest.setBirthDay(LocalDate.of(1980,2,2));
				adminRequest.setBirthPlace("Texas");*/

				userService.saveUser(adminRequest, "Admin");
			}
		}
	}


