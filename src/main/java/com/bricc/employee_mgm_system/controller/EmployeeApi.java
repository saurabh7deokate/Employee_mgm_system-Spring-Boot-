package com.bricc.employee_mgm_system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.bricc.employee_mgm_system.dto.Employee;
import com.bricc.employee_mgm_system.service.EmployeeService;
import com.bricc.employee_mgm_system.util.ResponseStructure;

@RestController
public class EmployeeApi {

	@Autowired
	private EmployeeService employeeService;

	/* API-01 */

	@PostMapping("/save")
	public ResponseStructure<Employee> saveEmployee(@RequestBody Employee employee) {

		return employeeService.saveEmployee(employee);
	}

	/* API-02 */

	@PostMapping("/saveAll")
	public List<Employee> saveAllEmployees(@RequestBody List<Employee> employees) {

		return employeeService.saveAllEmployees(employees);
	}

	/* API-03 */

	@GetMapping("/fetch")
	public ResponseStructure<Employee> fetchEmployee(@RequestParam Integer id) {

		return employeeService.fetchEmployee(id);
	}

	/* API-04 */

	@GetMapping("/fetchAll")
	public ResponseStructure<List<Employee>> fetchAllEmployee() {

		return employeeService.fetchAllEmployee();
	}

	/* API-05 */

	@DeleteMapping("/delete/{id}")
	public Employee deleteEmployee(@PathVariable Integer id) {
		return employeeService.deleteEmployee(id);
	}

	/* API-06 */

	@PutMapping("/updateEmployee")
	public ResponseStructure<Employee> updateEmployee(@RequestParam Integer id, @RequestBody Employee employee) {

		return employeeService.updateEmployee(id, employee);
	}

	/* API-07 */

	@PatchMapping("/updatePhone")
	public ResponseStructure<Employee> updatePhone(@RequestParam Integer id, @RequestParam Long phone) {

		return employeeService.updatePhone(id, phone);
	}

	/* API-08 */

	@PatchMapping("/updateEmail")
	public ResponseStructure<Employee> updateEmail(@RequestParam Integer id, @RequestParam String email) {

		return employeeService.updateEmail(id, email);
	}

	/* API-09 */

	@PatchMapping("/updateSalary")
	public ResponseStructure<Employee> updateSalary(@RequestParam Integer id, @RequestParam Double salary) {

		return employeeService.updateSalary(id, salary);
	}

	/* API-10 */

	@GetMapping("/fetchByPhone")
	public Employee fetchByPhone(@RequestParam Long phone) {

		return employeeService.fetchByPhone(phone);
	}

	/* API-11 */

	@GetMapping("/getByEmail")
	public Employee getByEmail(@RequestParam String email) {

		return employeeService.getByEmail(email);
	}

	/* API-12 */

	@GetMapping("/fetchByAddress")
	public List<Employee> fetchByAddress(@RequestParam String address) {

		return employeeService.fetchByAddress(address);
	}

	/* API-13 */

	@GetMapping("/fetchByName")
	public List<Employee> fetchByName(@RequestParam String name) {

		return employeeService.fetchByName(name);
	}

	/* API-14 */

	@GetMapping("/fetchBySalary")
	public List<Employee> fetchBySalary(@RequestParam Double salary) {

		return employeeService.fetchBySalary(salary);
	}

	/* API-15 */

	@GetMapping("/salLessThan")
	public List<Employee> salLessThan(@RequestParam Double salary) {

		return employeeService.salLessThan(salary);
	}

	/* API-16 */

	@GetMapping("/salBetween")
	public List<Employee> salBetween(@RequestParam Double lowSalary, @RequestParam Double highSalary) {

		return employeeService.salBetween(lowSalary, highSalary);
	}

	/* API-17 */

	@GetMapping("/fetchByGrade")
	public List<Employee> fetchByGrade(@RequestParam Character grade) {

		return employeeService.fetchByGrade(grade);
	}
}
