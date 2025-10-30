package com.incubyte.SalaryManagement.controller;

import com.incubyte.SalaryManagement.model.Employee;
import com.incubyte.SalaryManagement.repository.EmployeeRepository;
import com.incubyte.SalaryManagement.service.EmployeeService;

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
	public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
		Employee savedEmployee = employeeService.createEmployee(employee);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedEmployee);
	}
}