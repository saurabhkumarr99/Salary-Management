package com.incubyte.SalaryManagement.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.incubyte.SalaryManagement.model.Employee;
import com.incubyte.SalaryManagement.repository.EmployeeRepository;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class SalaryControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	EmployeeRepository employeeRepository;

	@Autowired
	private ObjectMapper objectMapper;

	String createEmpUrl = "/api/employeeService/createEmployee";
	String calculateSalaryUrl = "/api/salaryService/calculateSalary/";
	String salaryMetricsByCountryUrl = "/api/salaryService/getMetricsByCountry/";

	@BeforeEach
	void setUp() {
		employeeRepository.deleteAll();
	}

	// To test whether salary is calculated or not
	@Test
	void shouldCalculateNetSalaryBasedOnCountry() throws Exception {
		// Step 1: Create and save employee
		Employee employee = new Employee("John Doe", "Engineer", "India", 100000);
		MvcResult result = mockMvc
				.perform(post(createEmpUrl).contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(employee)))
				.andExpect(status().isCreated()).andReturn();

		Employee savedEmployee = objectMapper.readValue(result.getResponse().getContentAsString(), Employee.class);

		// Step 2: Call salary calculation endpoint
		mockMvc.perform(get(calculateSalaryUrl + savedEmployee.getId()).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(jsonPath("$.grossSalary").value(100000.0))
				.andExpect(jsonPath("$.tds").value(10000.0)).andExpect(jsonPath("$.netSalary").value(90000.0));
	}

	// To test salary metrics by country
	@Test
	void shouldReturnSalaryMetricsByCountry() throws Exception {

		// Step 1: Add employees directly
		employeeRepository.save(new Employee("John Doe", "Engineer", "India", 80000.0));
		employeeRepository.save(new Employee("Jane Smith", "Manager", "India", 120000.0));
		employeeRepository.save(new Employee("Chris Martin", "Engineer", "US", 95000.0));

		// Step 2: Fetch metrics by country
		mockMvc.perform(get(salaryMetricsByCountryUrl + "India")).andExpect(status().isOk())
				.andExpect(jsonPath("$.country").value("India")).andExpect(jsonPath("$.minSalary").value(80000.0))
				.andExpect(jsonPath("$.maxSalary").value(120000.0))
				.andExpect(jsonPath("$.averageSalary").value(100000.0));
	}
	
	// To test exception handeling when country does not exsit
	@Test
	void shouldThrowExceptionWhenCountryDoesNotExist() throws Exception {
	    // Step 1: Use a country not present in DB
	    String nonExistentCountry = "Atlantis";

	    // Step 2: Perform GET request to fetch salary metrics
	    mockMvc.perform(get(salaryMetricsByCountryUrl + nonExistentCountry)
	                    .contentType(MediaType.APPLICATION_JSON))
	            // Step 3: Expect NOT_FOUND and message
	            .andExpect(status().isNotFound())
	            .andExpect(jsonPath("$.message").value("Country not found: " + nonExistentCountry));
	}

}
