package com.example.sbercreditdepartment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class SberCreditDepartmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(SberCreditDepartmentApplication.class, args);

		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String hashedPassword = encoder.encode("123");
		System.out.println(hashedPassword);
	}

}
