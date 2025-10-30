package com.incubyte.SalaryManagement.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.incubyte.SalaryManagement.model.Employee;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	String createEmpUrl = "/api/employeeService/createEmployee";
	String getEmpByIdUrl = "/api/employeeService/getEmployeeById/";
	String updateEmpByIdUrl = "/api/employeeService/updateEmployee/";
	String deleteEmpByIdUrl = "/api/employeeService/deleteEmployee/";

	// Test Create Employee
	@Test
	void shouldCreateEmployeeSuccessfully() throws Exception {
		Employee employee = new Employee("John Doe", "Engineer", "India", 80000);

		mockMvc.perform(post(createEmpUrl).contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(employee))).andExpect(status().isCreated())
				.andExpect(jsonPath("$.id").exists()).andExpect(jsonPath("$.fullName").value("John Doe"))
				.andExpect(jsonPath("$.salary").value(80000));
	}

	// Test Validations for employee creation
	@Test
	void shouldFailWhenRequiredFieldsAreMissing() throws Exception {
		Employee employee = new Employee();
		// Missing required fields

		mockMvc.perform(post(createEmpUrl).contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(employee))).andExpect(status().isBadRequest()); // Expect 400
																											// due to
																											// validation
																											// failure
	}

	// Test case to verify fetching an employee by ID successfully.
	@Test
	void shouldFetchEmployeeByIdSuccessfully() throws Exception {
		// Step 1: Create a new employee using the API
		Employee employee = new Employee("John Doe", "Engineer", "India", 80000);

		MvcResult result = mockMvc
				.perform(post(createEmpUrl).contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(employee)))
				.andExpect(status().isCreated()).andReturn();

		String responseBody = result.getResponse().getContentAsString();
		Employee savedEmployee = objectMapper.readValue(responseBody, Employee.class);

		// Step 2: Fetch employee by ID using GET
		mockMvc.perform(get(getEmpByIdUrl + savedEmployee.getId()).contentType(MediaType.APPLICATION_JSON))

				// Step 3: Validate response
				.andExpect(status().isOk()).andExpect(jsonPath("$.fullName").value("John Doe"))
				.andExpect(jsonPath("$.jobTitle").value("Engineer")).andExpect(jsonPath("$.country").value("India"))
				.andExpect(jsonPath("$.salary").value(80000.0));
	}

	// Test case to verify employee not found exception
	@Test
	void shouldReturnNotFoundWhenEmployeeDoesNotExist() throws Exception {
		Long nonExistentId = 999L; // any ID not present in DB

		mockMvc.perform(get(getEmpByIdUrl + nonExistentId).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound())
				.andExpect(jsonPath("$.errors[0]").value("Employee not found with ID: " + nonExistentId));
	}

	// Test to verify whether update is happening or not
	@Test
	void shouldUpdateEmployeeWhenIdExists() throws Exception {
		// Step 1: Create and save employee first
		Employee employee = new Employee("John Doe", "Engineer", "India", 80000);
		MvcResult result = mockMvc
				.perform(post(createEmpUrl).contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(employee)))
				.andExpect(status().isCreated()).andReturn();

		String responseBody = result.getResponse().getContentAsString();
		Employee savedEmployee = objectMapper.readValue(responseBody, Employee.class);

		// Step 2: Prepare updated data
		Employee updatedEmployee = new Employee("John Updated", "Senior Engineer", "India", 90000);

		// Step 3: Perform PUT request
		mockMvc.perform(put(updateEmpByIdUrl + savedEmployee.getId()).contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(updatedEmployee))).andExpect(status().isOk())
				.andExpect(jsonPath("$.fullName").value("John Updated"))
				.andExpect(jsonPath("$.jobTitle").value("Senior Engineer"))
				.andExpect(jsonPath("$.salary").value(90000.0));
	}

	// Test to verify whether employee not found exception occur when update non
	// exist employee
	@Test
	void shouldReturnNotFoundWhenUpdatingNonExistingEmployee() throws Exception {
		Long nonExistentId = 999L; // ID that doesn't exist

		Employee updatedEmployee = new Employee("Jane Doe", "Manager", "USA", 120000);

		mockMvc.perform(put(updateEmpByIdUrl + nonExistentId).contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(updatedEmployee))).andExpect(status().isNotFound())
				.andExpect(jsonPath("$.errors[0]").value("Employee not found with ID: " + nonExistentId));
	}

	// Test to verify deletion api
	@Test
	void shouldDeleteEmployeeByIdSuccessfully() throws Exception {
		// Step 1: Create and save employee first
		Employee employee = new Employee("John Doe", "Engineer", "India", 80000);
		MvcResult result = mockMvc
				.perform(post(createEmpUrl).contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(employee)))
				.andExpect(status().isCreated()).andReturn();

		String responseBody = result.getResponse().getContentAsString();
		Employee savedEmployee = objectMapper.readValue(responseBody, Employee.class);

		// Step 2: Perform DELETE request to remove the employee
		mockMvc.perform(delete(deleteEmpByIdUrl + savedEmployee.getId()).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNoContent()); // HTTP 204

		// Step 3: Verify that the employee is deleted from DB
		mockMvc.perform(get(getEmpByIdUrl + savedEmployee.getId()).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound()); // Expected 404
	}

	// Test to verify exception handeling when employe not found and to be deleted
	@Test
	void shouldReturnNotFoundWhenDeletingNonExistentEmployee() throws Exception {
		// Step 1: Use a non-existing employee ID
		Long nonExistentId = 999L;

		// Step 2: Perform DELETE request for non-existent employee
		mockMvc.perform(delete(deleteEmpByIdUrl + nonExistentId).contentType(MediaType.APPLICATION_JSON))
				// Step 3: Expect 404 Not Found
				.andExpect(status().isNotFound())
				// Step 4: Validate the error message
				.andExpect(jsonPath("$.message").value("Employee Not Found"))
				.andExpect(jsonPath("$.errors[0]").value("Employee not found with ID: " + nonExistentId));
	}

}
