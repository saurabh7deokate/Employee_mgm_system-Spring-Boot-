package com.bricc.employee_mgm_system.service;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.bricc.employee_mgm_system.dao.EmployeeDao;
import com.bricc.employee_mgm_system.dto.Employee;
import com.bricc.employee_mgm_system.util.ResponseStructure;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeDao employeeDao;

	private static <T> ResponseStructure<T> setResponseStucture(String message, HttpStatus status, T data) {

		return new ResponseStructure<>(message, status.value(), data);
	}

	public ResponseStructure<Employee> saveEmployee(Employee employee) {

		Double salary = employee.getSalary();
		Character grade = (salary < 10000) ? 'D' : (salary < 20000) ? 'C' : (salary < 40000) ? 'B' : 'A';
		employee.setGrade(grade);

		ResponseStructure<Employee> responseStructure = setResponseStucture("Data Saved Successfully",
				HttpStatus.CREATED, employeeDao.saveEmployee(employee));
		return responseStructure;
	}

	public ResponseStructure<List<Employee>> saveAllEmployees(List<Employee> employees) {
		for (Employee employee : employees) {
			saveEmployee(employee);
		}
		return setResponseStucture("All Data Saved Successfully", HttpStatus.CREATED, employees);
	}

	public ResponseStructure<Employee> fetchEmployee(Integer id) {
		return (employeeDao.fetchEmployee(id) != null)
				? setResponseStucture("Data Found Successfully", HttpStatus.FOUND, employeeDao.fetchEmployee(id))
				: setResponseStucture("Data Not Found", HttpStatus.NOT_FOUND, employeeDao.fetchEmployee(id));
	}

	public ResponseStructure<List<Employee>> fetchAllEmployee() {
		return (!employeeDao.fetchAllEmployee().isEmpty())
				? setResponseStucture("Data Found Successfully", HttpStatus.FOUND, employeeDao.fetchAllEmployee())
				: setResponseStucture("Data Not Found", HttpStatus.NOT_FOUND, employeeDao.fetchAllEmployee());
	}

	public ResponseStructure<Employee> deleteEmployee(Integer id) {
		Employee employee = employeeDao.fetchEmployee(id);
		return (employee != null)
				? setResponseStucture("Data Deleted Successfully", HttpStatus.FOUND,
						employeeDao.deleteEmployee(employee))
				: setResponseStucture("Id Not Found", HttpStatus.NOT_FOUND, employee);
	}

	public ResponseStructure<Employee> updateEmployee(Integer id, Employee employee) {
		Employee dBEmployee = employeeDao.fetchEmployee(id);
		if (dBEmployee != null) {
			employee.setId(id);
			saveEmployee(employee);
			return setResponseStucture("Data Updated Successfully", HttpStatus.CREATED, employee);
		}
		return setResponseStucture("Id Not Found", HttpStatus.NOT_FOUND, null);
	}

	public ResponseStructure<Employee> updatePhone(Integer id, Long phone) {
		Employee employee = employeeDao.fetchEmployee(id);
		if (employee != null) {
			employee.setPhone(phone);
			saveEmployee(employee);
			return setResponseStucture("Phone Updated Successfully", HttpStatus.CREATED, employee);
		}
		return setResponseStucture("Id Not Found", HttpStatus.NOT_FOUND, null);
	}

	public ResponseStructure<Employee> updateEmail(Integer id, String email) {
		Employee employee = employeeDao.fetchEmployee(id);
		if (employee != null) {
			employee.setEmail(email);
			saveEmployee(employee);
			return setResponseStucture("Email Updated Successfully", HttpStatus.CREATED, employee);
		}
		return setResponseStucture("Id Not Found", HttpStatus.NOT_FOUND, null);
	}

	public ResponseStructure<Employee> updateSalary(Integer id, Double salary) {
		Employee employee = employeeDao.fetchEmployee(id);
		if (employee != null) {
			employee.setSalary(salary);
			saveEmployee(employee);
			return setResponseStucture("Salary Updated Successfully", HttpStatus.CREATED, employee);
		}
		return setResponseStucture("Id Not Found", HttpStatus.NOT_FOUND, null);
	}

	public ResponseStructure<Employee> fetchByPhone(Long phone) {
		return (employeeDao.fetchByPhone(phone) != null)
				? setResponseStucture("Data Found Successfully", HttpStatus.FOUND, employeeDao.fetchByPhone(phone))
				: setResponseStucture("Phone Not Found", HttpStatus.NOT_FOUND, null);
	}

	public ResponseStructure<Employee> getByEmail(String email) {
		return (employeeDao.getByEmail(email) != null)
				? setResponseStucture("Data Found Successfully", HttpStatus.FOUND, employeeDao.getByEmail(email))
				: setResponseStucture("Email Not Found", HttpStatus.NOT_FOUND, null);
	}

	public ResponseStructure<List<Employee>> fetchByAddress(String address) {
		return (!employeeDao.fetchByAddress(address).isEmpty())
				? setResponseStucture("Data Found Successfully", HttpStatus.FOUND, employeeDao.fetchByAddress(address))
				: setResponseStucture("Address Not Found", HttpStatus.NOT_FOUND, null);
	}

	public ResponseStructure<List<Employee>> fetchByName(String name) {
		return (!employeeDao.fetchByName(name).isEmpty())
				? setResponseStucture("Data Found Successfully", HttpStatus.FOUND, employeeDao.fetchByName(name))
				: setResponseStucture("Name Not Found", HttpStatus.NOT_FOUND, null);
	}

	public ResponseStructure<List<Employee>> fetchBySalary(Double salary) {
		return (!employeeDao.fetchBySalary(salary).isEmpty())
				? setResponseStucture("Data Found Successfully", HttpStatus.FOUND, employeeDao.fetchBySalary(salary))
				: setResponseStucture("Salary Not Found", HttpStatus.NOT_FOUND, null);
	}

	public ResponseStructure<List<Employee>> salLessThan(Double salary) {
		return (!employeeDao.salLessThan(salary).isEmpty())
				? setResponseStucture("Data Found Successfully", HttpStatus.FOUND, employeeDao.salLessThan(salary))
				: setResponseStucture("Salaries Less Than " + String.valueOf(salary) + " Not Found",
						HttpStatus.NOT_FOUND, null);
	}

	public ResponseStructure<List<Employee>> salBetween(Double lowSalary, Double highSalary) {

		return (!employeeDao.salBetween(lowSalary, highSalary).isEmpty())
				? setResponseStucture("Data Found Successfully", HttpStatus.FOUND,
						employeeDao.salBetween(lowSalary, highSalary))
				: setResponseStucture("Salary Between Specified range Not Found", HttpStatus.NOT_FOUND, null);
	}

	public ResponseStructure<List<Employee>> fetchByGrade(Character grade) {
		return (!employeeDao.fetchByGrade(grade).isEmpty())
				? setResponseStucture("Data Found Successfully", HttpStatus.FOUND, employeeDao.fetchByGrade(grade))
				: setResponseStucture("Invalid Grade", HttpStatus.NOT_FOUND, null);
	}
}