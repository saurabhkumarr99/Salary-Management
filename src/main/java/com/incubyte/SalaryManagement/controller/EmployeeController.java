package com.incubyte.SalaryManagement.controller;

import com.incubyte.SalaryManagement.model.Employee;
import com.incubyte.SalaryManagement.repository.EmployeeRepository;
import com.incubyte.SalaryManagement.service.EmployeeService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/employeeService")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	/**
	 * @author Saurabh Rai
	 * @apiNote Create Employee
	 * @param employee
	 * @return
	 */
	@PostMapping("/createEmployee")
	public ResponseEntity<Employee> createEmployee(@Valid @RequestBody Employee employee) {
		Employee savedEmployee = employeeService.createEmployee(employee);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedEmployee);
	}

	/**
	 * @author Saurah Rai
	 * @apiNote Get employee by id
	 * @param id
	 * @return
	 */
	@GetMapping("/getEmployeeById/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
		Employee employee = employeeService.getEmployeeById(id);
		return ResponseEntity.status(HttpStatus.OK).body(employee);
	}

	/**
	 * @author Saurabh Rai
	 * @param id
	 * @param updatedEmployee
	 * @return
	 */
	@PutMapping("/updateEmployee/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable Long id,
			@Valid @RequestBody Employee updatedEmployee) {

		Employee employee = employeeService.updateEmployee(id, updatedEmployee);
		return ResponseEntity.status(HttpStatus.OK).body(employee);
	}
}