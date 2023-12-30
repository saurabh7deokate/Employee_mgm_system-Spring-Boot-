package com.bricc.employee_mgm_system.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bricc.employee_mgm_system.dto.Employee;
import com.bricc.employee_mgm_system.repo.EmployeeRepo;

@Repository
public class EmployeeDao {

	@Autowired
	private EmployeeRepo employeeRepo;

	public Employee saveEmployee(Employee employee) {

		return employeeRepo.save(employee);
	}

	/* TODO: Remove */

//	public List<Employee> saveAllEmployees(List<Employee> employees) {
//
//		return employeeRepo.saveAll(employees);
//	}

	public Employee fetchEmployee(Integer id) {

		Optional<Employee> optional = employeeRepo.findById(id);
		if (optional.isPresent()) {
			return optional.get();
		}
		return null;

		/* Alternate */

//		Optional<Employee> optional1 = employeeRepo.findById(id);
//		if(optional1.isEmpty()) {
//			return null;
//		}
//		return optional.get();

	}

	public List<Employee> fetchAllEmployee() {

		return employeeRepo.findAll();
	}

	public Employee deleteEmployee(Employee employee) {

		employeeRepo.delete(employee);
		return employee;
	}

	/* TODO: Remove */

//	public Employee updateEmployee(Integer id, Employee employee) {
//
//		Optional<Employee> optional = employeeRepo.findById(id);
//		if (optional.isPresent()) {
//			employee.setId(id);
//			return employeeRepo.save(employee);
//		}
//		return null;
//	}

	/* TODO: Remove */

//	public Employee updatePhone(Integer id, Long phone) {
//
//		Optional<Employee> optional = employeeRepo.findById(id);
//		if (optional.isPresent()) {
//			Employee employee = optional.get();
//			employee.setPhone(phone);
//			return employeeRepo.save(employee);
//		}
//		return null;
//	}

	/* TODO: Remove */

//	public Employee updateEmail(Integer id, String email) {
//
//		Optional<Employee> optional = employeeRepo.findById(id);
//		if (optional.isPresent()) {
//			Employee employee = optional.get();
//			employee.setEmail(email);
//			return employeeRepo.save(employee);
//		}
//		return null;
//	}

	/* TODO: Remove */

//	public Employee updateSalary(Integer id, Double salary) {
//
//		Optional<Employee> optional = employeeRepo.findById(id);
//		if (optional.isPresent()) {
//			Employee employee = optional.get();
//			employee.setSalary(salary);
//			return employeeRepo.save(employee);
//		}
//		return null;
//	}

	public Employee fetchByPhone(Long phone) {

		return employeeRepo.findEmployeeByPhone(phone);
	}

	public Employee getByEmail(String email) {

		return employeeRepo.getEmployeeByEmail(email);
	}

	public List<Employee> fetchByAddress(String address) {

		return employeeRepo.findEmpByThierAddress(address);
	}

	public List<Employee> fetchByName(String name) {

		return employeeRepo.findEmpByThierName(name);
	}

	public List<Employee> fetchBySalary(Double salary) {
		
		return employeeRepo.findEmployeeByTheirSalary(salary);
	}

	public List<Employee> salLessThan(Double salary) {

		return employeeRepo.findEmployeesBySalaryLessThan(salary);
	}

	public List<Employee> salBetween(Double lowSalary, Double highSalary) {

		return employeeRepo.empSalBetween(lowSalary, highSalary);
	}

	public List<Employee> fetchByGrade(Character grade) {
		
		return employeeRepo.findEmployeesByGrade(grade);
	}
}
