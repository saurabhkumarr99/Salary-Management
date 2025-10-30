package com.incubyte.SalaryManagement.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.incubyte.SalaryManagement.model.Employee;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper; 
	
	String createEmpUrl = "/api/employeeService/createEmployee";

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

        mockMvc.perform(post(createEmpUrl)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(employee)))
                .andExpect(status().isBadRequest()); // Expect 400 due to validation failure
    }
}
