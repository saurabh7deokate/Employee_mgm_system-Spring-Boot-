package com.bricc.employee_mgm_system.service;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.bricc.employee_mgm_system.dao.EmployeeDao;
import com.bricc.employee_mgm_system.dto.Employee;
import com.bricc.employee_mgm_system.exception.DataNotFoundException;
import com.bricc.employee_mgm_system.exception.IdNotFoundException;
import com.bricc.employee_mgm_system.exception.NotFoundException;
import com.bricc.employee_mgm_system.util.ResponseStructure;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeDao employeeDao;

	public static <T> ResponseEntity<ResponseStructure<T>> setResponse(String message, HttpStatus status, T data) {

		ResponseStructure<T> responseStructure = new ResponseStructure<T>(message, status.value(), data);
		return new ResponseEntity<>(responseStructure, status);

	}

	public ResponseEntity<ResponseStructure<Employee>> saveEmployee(Employee employee) {

		Double salary = employee.getSalary();
		Character grade = (salary < 10000) ? 'D' : (salary < 20000) ? 'C' : (salary < 40000) ? 'B' : 'A';
		employee.setGrade(grade);

		return setResponse("Data Saved Successfully", HttpStatus.CREATED, employeeDao.saveEmployee(employee));
	}

	public ResponseEntity<ResponseStructure<List<Employee>>> saveAllEmployees(List<Employee> employees) {
		for (Employee employee : employees) {
			saveEmployee(employee);
		}

		return setResponse("All Data Saved Successfully", HttpStatus.CREATED, employees);
	}

	public ResponseEntity<ResponseStructure<Employee>> fetchEmployee(Integer id) {
		if (employeeDao.fetchEmployee(id) != null)
			return setResponse("Data Found Successfully", HttpStatus.FOUND, employeeDao.fetchEmployee(id));
		else
			throw new DataNotFoundException("Data Not Found");
	}

	public ResponseEntity<ResponseStructure<List<Employee>>> fetchAllEmployee() {
		if (!employeeDao.fetchAllEmployee().isEmpty())
			return setResponse("Data Found Successfully", HttpStatus.FOUND, employeeDao.fetchAllEmployee());
		else
			throw new DataNotFoundException("Data Not Found");
	}

	public ResponseEntity<ResponseStructure<Employee>> deleteEmployee(Integer id) {
		Employee employee = employeeDao.fetchEmployee(id);
		if (employee != null) {
			return setResponse("Data Deleted Successfully", HttpStatus.OK, employeeDao.deleteEmployee(employee));
		} else {
			throw new IdNotFoundException("ID Not found to Delete The Employee");
		}

	}

	public ResponseEntity<ResponseStructure<Employee>> updateEmployee(Integer id, Employee employee) {
		Employee dBEmployee = employeeDao.fetchEmployee(id);
		if (dBEmployee != null) {
			employee.setId(id);
			saveEmployee(employee);
			return setResponse("Data Updated Successfully", HttpStatus.OK, employee);
		} else
			throw new IdNotFoundException("ID Not Found");
	}

	public ResponseEntity<ResponseStructure<Employee>> updatePhone(Integer id, Long phone) {
		Employee employee = employeeDao.fetchEmployee(id);
		if (employee != null) {
			employee.setPhone(phone);
			saveEmployee(employee);
			return setResponse("Phone Updated Successfully", HttpStatus.OK, employee);
		} else
			throw new IdNotFoundException("ID Not Found");
	}

	public ResponseEntity<ResponseStructure<Employee>> updateEmail(Integer id, String email) {
		Employee employee = employeeDao.fetchEmployee(id);
		if (employee != null) {
			employee.setEmail(email);
			saveEmployee(employee);
			return setResponse("Email Updated Successfully", HttpStatus.OK, employee);
		} else
			throw new IdNotFoundException("ID Not Found");
	}

	public ResponseEntity<ResponseStructure<Employee>> updateSalary(Integer id, Double salary) {
		Employee employee = employeeDao.fetchEmployee(id);
		if (employee != null) {
			employee.setSalary(salary);
			saveEmployee(employee);
			return setResponse("Salary Updated Successfully", HttpStatus.OK, employee);
		} else
			throw new IdNotFoundException("ID Not Found");
	}

	public ResponseEntity<ResponseStructure<Employee>> fetchByPhone(Long phone) {
		if (employeeDao.fetchByPhone(phone) != null)
			return setResponse("Data Found Successfully", HttpStatus.FOUND, employeeDao.fetchByPhone(phone));
		else
			throw new NotFoundException("Phone Not Found");
	}

	public ResponseEntity<ResponseStructure<Employee>> getByEmail(String email) {
		if (employeeDao.getByEmail(email) != null)
			return setResponse("Data Found Successfully", HttpStatus.FOUND, employeeDao.getByEmail(email));
		else
			throw new NotFoundException("Email Not Found");
	}

	public ResponseEntity<ResponseStructure<List<Employee>>> fetchByAddress(String address) {
		if (!employeeDao.fetchByAddress(address).isEmpty())
			return setResponse("Data Found Successfully", HttpStatus.FOUND, employeeDao.fetchByAddress(address));
		else
			throw new NotFoundException("Address Not Found");
	}

	public ResponseEntity<ResponseStructure<List<Employee>>> fetchByName(String name) {
		if (!employeeDao.fetchByName(name).isEmpty())
			return setResponse("Data Found Successfully", HttpStatus.FOUND, employeeDao.fetchByName(name));
		else
			throw new NotFoundException("Name Not Found");
	}

	public ResponseEntity<ResponseStructure<List<Employee>>> fetchBySalary(Double salary) {
		if (!employeeDao.fetchBySalary(salary).isEmpty())
			return setResponse("Data Found Successfully", HttpStatus.FOUND, employeeDao.fetchBySalary(salary));
		else
			throw new NotFoundException("Salary Not Found");
	}

	public ResponseEntity<ResponseStructure<List<Employee>>> salLessThan(Double salary) {
		if (!employeeDao.salLessThan(salary).isEmpty())
			return setResponse("Data Found Successfully", HttpStatus.FOUND, employeeDao.salLessThan(salary));
		else
			throw new NotFoundException("Salary Not Found");
	}

	public ResponseEntity<ResponseStructure<List<Employee>>> salBetween(Double lowSalary, Double highSalary) {
		if (!employeeDao.salBetween(lowSalary, highSalary).isEmpty())
			return setResponse("Data Found Successfully", HttpStatus.FOUND,
					employeeDao.salBetween(lowSalary, highSalary));
		else
			throw new NotFoundException("Salary Between Specified Range Not Found");
	}

	public ResponseEntity<ResponseStructure<List<Employee>>> fetchByGrade(Character grade) {
		if (!employeeDao.fetchByGrade(grade).isEmpty())
			return setResponse("Data Found Successfully", HttpStatus.FOUND, employeeDao.fetchByGrade(grade));
		else
			throw new NotFoundException("Invalid Grade");
	}
}