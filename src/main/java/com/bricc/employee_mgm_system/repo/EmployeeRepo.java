package com.bricc.employee_mgm_system.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bricc.employee_mgm_system.dto.Employee;

public interface EmployeeRepo extends JpaRepository<Employee, Integer> {

	Employee findEmployeeByPhone(Long phone);

	Employee getEmployeeByEmail(String email);

	@Query("SELECT employee FROM Employee employee WHERE employee.address = ?1")
	List<Employee> findEmpByThierAddress(String address);

	@Query("SELECT employee FROM Employee employee WHERE employee.name = ?1")
	List<Employee> findEmpByThierName(String name);

	@Query("SELECT employee FROM Employee employee WHERE employee.salary = ?1")
	List<Employee> findEmployeeByTheirSalary(Double salary);

	List<Employee> findEmployeesBySalaryLessThan(Double salary); // No need of a query

	@Query("SELECT employee FROM Employee employee WHERE employee.salary BETWEEN ?1 AND ?2")
	List<Employee> empSalBetween(Double lowSalary, Double highSalary);

	@Query("SELECT employee FROM Employee employee WHERE employee.grade = ?1")
	List<Employee> findEmployeesByGrade(Character grade);

}
