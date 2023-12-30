package com.bricc.employee_mgm_system.service;

import java.util.List;
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

	public static void saveGrade(Employee employee) {

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

	public ResponseStructure<Employee> saveEmployee(Employee employee) {

		EmployeeService.saveGrade(employee);

		ResponseStructure<Employee> responseStructure = new ResponseStructure<Employee>();
		responseStructure.setMessage("Data Saved Succesfully");
		responseStructure.setStatus(HttpStatus.CREATED.value());
		responseStructure.setData(employeeDao.saveEmployee(employee));

		return responseStructure;
	}

	public List<Employee> saveAllEmployees(List<Employee> employees) {

		for (Employee employee : employees) {
			saveEmployee(employee);
		}
		return employees;
	}

	public ResponseStructure<Employee> fetchEmployee(Integer id) {

		ResponseStructure<Employee> responseStructure = new ResponseStructure<>();

		if (employeeDao.fetchEmployee(id) != null) {
			responseStructure.setMessage("Data Found Succesfully");
			responseStructure.setStatus(HttpStatus.FOUND.value());
			responseStructure.setData(employeeDao.fetchEmployee(id));
			return responseStructure;
		}
		responseStructure.setMessage("Data Not Found");
		responseStructure.setStatus(HttpStatus.NOT_FOUND.value());
		responseStructure.setData(employeeDao.fetchEmployee(id));
		return responseStructure;
	}

	public ResponseStructure<List<Employee>> fetchAllEmployee() {

		ResponseStructure<List<Employee>> responseStructure = new ResponseStructure<>();
		if (!employeeDao.fetchAllEmployee().isEmpty()) {
			responseStructure.setMessage("Data Found Succesfully");
			responseStructure.setStatus(HttpStatus.FOUND.value());
			responseStructure.setData(employeeDao.fetchAllEmployee());
			return responseStructure;
		}
		responseStructure.setMessage("Data Not Found");
		responseStructure.setStatus(HttpStatus.NOT_FOUND.value());
		responseStructure.setData(employeeDao.fetchAllEmployee());
		return responseStructure;
	}

	public Employee deleteEmployee(Integer id) {

		Employee employee = employeeDao.fetchEmployee(id);

		if (employee != null) {
			return employeeDao.deleteEmployee(employee);
		}
		return null;
	}

	public ResponseStructure<Employee> updateEmployee(Integer id, Employee employee) {

		Employee dBEmployee = employeeDao.fetchEmployee(id);

		if (dBEmployee != null) {
			employee.setId(id);
			return saveEmployee(employee);
		}
		return null;
	}

	public ResponseStructure<Employee> updatePhone(Integer id, Long phone) {

		Employee employee = employeeDao.fetchEmployee(id);
		if (employee != null) {
			employee.setPhone(phone);
			return saveEmployee(employee);
		}
		return null;
	}

	public ResponseStructure<Employee> updateEmail(Integer id, String email) {

		Employee employee = employeeDao.fetchEmployee(id);
		if (employee != null) {
			employee.setEmail(email);
			return saveEmployee(employee);
		}
		return null;
	}

	public ResponseStructure<Employee> updateSalary(Integer id, Double salary) {

		Employee employee = employeeDao.fetchEmployee(id);
		if (employee != null) {
			employee.setSalary(salary);
			return saveEmployee(employee);
		}
		return null;
	}

	public Employee fetchByPhone(Long phone) {

		return employeeDao.fetchByPhone(phone);
	}

	public Employee getByEmail(String email) {

		return employeeDao.getByEmail(email);
	}

	public List<Employee> fetchByAddress(String address) {

		return employeeDao.fetchByAddress(address);
	}

	public List<Employee> fetchByName(String name) {

		return employeeDao.fetchByName(name);
	}

	public List<Employee> fetchBySalary(Double salary) {

		return employeeDao.fetchBySalary(salary);
	}

	public List<Employee> salLessThan(Double salary) {

		return employeeDao.salLessThan(salary);
	}

	public List<Employee> salBetween(Double lowSalary, Double highSalary) {

		return employeeDao.salBetween(lowSalary, highSalary);
	}

	public List<Employee> fetchByGrade(Character grade) {

		return employeeDao.fetchByGrade(grade);
	}

}
