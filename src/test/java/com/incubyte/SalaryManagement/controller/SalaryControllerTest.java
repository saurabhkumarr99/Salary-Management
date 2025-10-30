package com.incubyte.SalaryManagement.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.incubyte.SalaryManagement.model.Employee;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class SalaryControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	String createEmpUrl = "/api/employeeService/createEmployee";
	String calculateSalaryUrl = "/api/salaryService/calculateSalary/";

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

}
