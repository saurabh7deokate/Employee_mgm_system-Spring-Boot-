package com.bricc.employee_mgm_system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class EmployeeMgmSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeeMgmSystemApplication.class, args);
	}
}