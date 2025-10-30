package com.incubyte.SalaryManagement.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.incubyte.SalaryManagement.exceptions.EmployeeNotFoundException;
import com.incubyte.SalaryManagement.model.Employee;
import com.incubyte.SalaryManagement.repository.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	EmployeeRepository employeeRepository;

	private static final Logger logger = LoggerFactory.getLogger(EmployeeService.class);

	// Create Employee
	public Employee createEmployee(@RequestBody Employee employee) {

		logger.info("Employee created successfully : " + employee);
		return employeeRepository.save(employee);
	}

	// Get employee by id
	public Employee getEmployeeById(Long id) {
		return employeeRepository.findById(id)
				.orElseThrow(() -> new EmployeeNotFoundException("Employee not found with ID: " + id));
	}

	// Update employee by id
	public Employee updateEmployee(Long id, Employee updatedEmployee) {
		Employee existingEmployee = employeeRepository.findById(id).get();

		// Update fields
		updatedEmployee.setId(id);
		return employeeRepository.save(updatedEmployee);
	}
}
