package com.incubyte.SalaryManagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.incubyte.SalaryManagement.dto.SalaryBreakupDto;
import com.incubyte.SalaryManagement.dto.SalaryMetricsDto;
import com.incubyte.SalaryManagement.service.SalaryService;

@RestController
@RequestMapping("/api/salaryService")
public class SalaryController {

	@Autowired
	SalaryService salaryService;

	// Calculate Salary Break up base of employee
	@GetMapping("/calculateSalary/{id}")
	public ResponseEntity<SalaryBreakupDto> calculateSalary(@PathVariable Long id) {

		SalaryBreakupDto salaryBreakupDto = salaryService.calculateSalaryBreakup(id);

		return ResponseEntity.status(HttpStatus.OK).body(salaryBreakupDto);
	}

	// Get salary metrics by country
	@GetMapping("getMetricsByCountry/{country}")
	public ResponseEntity<SalaryMetricsDto> getSalaryMetricsByCountry(@PathVariable String country) {
		SalaryMetricsDto metrics = salaryService.getSalaryMetricsByCountry(country);
		return ResponseEntity.status(HttpStatus.OK).body(metrics);
	}

	// Get salary metrics by country
	@GetMapping("getAvgSalryByTitle/{title}")
	public ResponseEntity<SalaryMetricsDto> getAvgSalaryMetricsByTitle(@PathVariable String title) {
		SalaryMetricsDto metrics = salaryService.getAverageSalaryByJobTitle(title);
		return ResponseEntity.status(HttpStatus.OK).body(metrics);
	}
}
