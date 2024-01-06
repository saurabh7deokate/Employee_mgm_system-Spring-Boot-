package com.bricc.employee_mgm_system.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotBlank(message = "Name is mandatory")
	@NotNull(message = "Name Cannot be null")
	private String name;

	@Column(unique = true)
	@Min(value = 6000000000l)
	@Max(value = 9999999999l)
	private Long phone;

	@NotBlank(message = "Address is mandatory")
	@NotNull(message = "Address Cannot be null")
	private String address;

	@Column(unique = true)
	@Email(regexp = "[a-z0-9._%-]+@[a-z0-9.-]+\\.[a-z]{2,4}", message = "Invalid Email")
	@NotBlank(message = "Email is mandatory")
	@NotNull(message = "Email Cannot be null")
	private String email;

	@Min(value = 1)
	private Double salary;

	private Character grade;
}