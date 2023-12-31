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

	private static void saveGrade(Employee employee) {

		if (employee.getSalary() < 10000 && employee.getSalary() >= 0) {

			employee.setGrade('D');

		} else if (employee.getSalary() >= 10000 && employee.getSalary() < 20000) {

			employee.setGrade('C');

		} else if (employee.getSalary() >= 20000 && employee.getSalary() < 40000) {

			employee.setGrade('B');

		} else {

			employee.setGrade('A');
		}
	}

	private static <T> ResponseStructure<T> setResponseStucture(String message, Integer status, T data) {
		ResponseStructure<T> responseStructure = new ResponseStructure<>();
		responseStructure.setMessage(message);
		responseStructure.setStatus(status);
		responseStructure.setData(data);
		return responseStructure;
	}

	public ResponseStructure<Employee> saveEmployee(Employee employee) {

		EmployeeService.saveGrade(employee);

		ResponseStructure<Employee> responseStructure = setResponseStucture("Data Saved Successfully",
				HttpStatus.CREATED.value(), employeeDao.saveEmployee(employee));
		return responseStructure;
	}

	public ResponseStructure<List<Employee>> saveAllEmployees(List<Employee> employees) {
		for (Employee employee : employees) {
			saveEmployee(employee);
		}
		return setResponseStucture("All Data Saved Successfully", HttpStatus.CREATED.value(), employees);
	}

	public ResponseStructure<Employee> fetchEmployee(Integer id) {
		if (employeeDao.fetchEmployee(id) != null) {
			return setResponseStucture("Data Found Successfully", HttpStatus.FOUND.value(),
					employeeDao.fetchEmployee(id));
		}
		return setResponseStucture("Data Not Found", HttpStatus.NOT_FOUND.value(), employeeDao.fetchEmployee(id));
	}

	public ResponseStructure<List<Employee>> fetchAllEmployee() {
		if (!employeeDao.fetchAllEmployee().isEmpty()) {
			return setResponseStucture("Data Found Successfully", HttpStatus.FOUND.value(),
					employeeDao.fetchAllEmployee());
		}
		return setResponseStucture("Data Not Found", HttpStatus.NOT_FOUND.value(), employeeDao.fetchAllEmployee());
	}

	public ResponseStructure<Employee> deleteEmployee(Integer id) {
		Employee employee = employeeDao.fetchEmployee(id);
		if (employee != null) {
			return setResponseStucture("Data Deleted Successfully", HttpStatus.FOUND.value(),
					employeeDao.deleteEmployee(employee));
		}
		return setResponseStucture("Id Not Found", HttpStatus.NOT_FOUND.value(), employee);
	}

	public ResponseStructure<Employee> updateEmployee(Integer id, Employee employee) {
		Employee dBEmployee = employeeDao.fetchEmployee(id);
		if (dBEmployee != null) {
			employee.setId(id);
			saveEmployee(employee);
			return setResponseStucture("Data Updated Successfully", HttpStatus.CREATED.value(), employee);
		}
		return setResponseStucture("Id Not Found", HttpStatus.NOT_FOUND.value(), null);
	}

	public ResponseStructure<Employee> updatePhone(Integer id, Long phone) {
		Employee employee = employeeDao.fetchEmployee(id);
		if (employee != null) {
			employee.setPhone(phone);
			saveEmployee(employee);
			return setResponseStucture("Phone Updated Successfully", HttpStatus.CREATED.value(), employee);
		}
		return setResponseStucture("Id Not Found", HttpStatus.NOT_FOUND.value(), null);
	}

	public ResponseStructure<Employee> updateEmail(Integer id, String email) {
		Employee employee = employeeDao.fetchEmployee(id);
		if (employee != null) {
			employee.setEmail(email);
			saveEmployee(employee);
			return setResponseStucture("Email Updated Successfully", HttpStatus.CREATED.value(), employee);
		}
		return setResponseStucture("Id Not Found", HttpStatus.NOT_FOUND.value(), null);
	}

	public ResponseStructure<Employee> updateSalary(Integer id, Double salary) {
		Employee employee = employeeDao.fetchEmployee(id);
		if (employee != null) {
			employee.setSalary(salary);
			saveEmployee(employee);
			return setResponseStucture("Salary Updated Successfully", HttpStatus.CREATED.value(), employee);
		}
		return setResponseStucture("Id Not Found", HttpStatus.NOT_FOUND.value(), null);
	}

	public ResponseStructure<Employee> fetchByPhone(Long phone) {
		if (employeeDao.fetchByPhone(phone) != null) {
			return setResponseStucture("Data Found Successfully", HttpStatus.FOUND.value(),
					employeeDao.fetchByPhone(phone));
		}
		return setResponseStucture("Phone Not Found", HttpStatus.NOT_FOUND.value(), null);
	}

	public ResponseStructure<Employee> getByEmail(String email) {
		if (employeeDao.getByEmail(email) != null) {
			return setResponseStucture("Data Found Successfully", HttpStatus.FOUND.value(),
					employeeDao.getByEmail(email));
		}
		return setResponseStucture("Email Not Found", HttpStatus.NOT_FOUND.value(), null);
	}

	public ResponseStructure<List<Employee>> fetchByAddress(String address) {
		if (!employeeDao.fetchByAddress(address).isEmpty()) {
			return setResponseStucture("Data Found Successfully", HttpStatus.FOUND.value(),
					employeeDao.fetchByAddress(address));
		} else {
			return setResponseStucture("Address Not Found", HttpStatus.NOT_FOUND.value(), null);
		}
	}

	public ResponseStructure<List<Employee>> fetchByName(String name) {
		if (!employeeDao.fetchByName(name).isEmpty()) {
			return setResponseStucture("Data Found Successfully", HttpStatus.FOUND.value(),
					employeeDao.fetchByName(name));
		}
		return setResponseStucture("Name Not Found", HttpStatus.NOT_FOUND.value(), null);
	}

	public ResponseStructure<List<Employee>> fetchBySalary(Double salary) {
		if (!employeeDao.fetchBySalary(salary).isEmpty()) {
			return setResponseStucture("Data Found Successfully", HttpStatus.FOUND.value(),
					employeeDao.fetchBySalary(salary));
		}
		return setResponseStucture("Salary Not Found", HttpStatus.NOT_FOUND.value(), null);
	}

	public ResponseStructure<List<Employee>> salLessThan(Double salary) {
		if (!employeeDao.salLessThan(salary).isEmpty()) {
			return setResponseStucture("Data Found Successfully", HttpStatus.FOUND.value(),
					employeeDao.salLessThan(salary));
		}
		return setResponseStucture("Salaries Less Than " + String.valueOf(salary) + " Not Found",
				HttpStatus.NOT_FOUND.value(), null);
	}

	public ResponseStructure<List<Employee>> salBetween(Double lowSalary, Double highSalary) {

		if (!employeeDao.salBetween(lowSalary, highSalary).isEmpty()) {
			return setResponseStucture("Data Found Successfully", HttpStatus.FOUND.value(),
					employeeDao.salBetween(lowSalary, highSalary));
		}
		String lowsal = String.valueOf(lowSalary);
		String highsal = String.valueOf(highSalary);
		String message = "Salaries Between " + lowsal + " and " + highsal + " Not Found";
		return setResponseStucture(message, HttpStatus.NOT_FOUND.value(), null);
	}

	public ResponseStructure<List<Employee>> fetchByGrade(Character grade) {
		if (!employeeDao.fetchByGrade(grade).isEmpty()) {
			return setResponseStucture("Data Found Successfully", HttpStatus.FOUND.value(),
					employeeDao.fetchByGrade(grade));
		}
		return setResponseStucture("Invalid Grade", HttpStatus.NOT_FOUND.value(), null);
	}
}
