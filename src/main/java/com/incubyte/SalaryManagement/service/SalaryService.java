package com.incubyte.SalaryManagement.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.incubyte.SalaryManagement.dto.EmployeeDto;
import com.incubyte.SalaryManagement.dto.SalaryBreakupDto;
import com.incubyte.SalaryManagement.dto.SalaryMetricsDto;
import com.incubyte.SalaryManagement.exceptions.CountryNotFoundException;
import com.incubyte.SalaryManagement.exceptions.EmployeeNotFoundException;
import com.incubyte.SalaryManagement.model.Employee;
import com.incubyte.SalaryManagement.repository.EmployeeRepository;
import com.incubyte.SalaryManagement.repository.SalaryRepository;

@Service
public class SalaryService {

	@Autowired
	EmployeeRepository employeeRepository;

	@Autowired
	SalaryRepository salaryRepository;

	private static final Logger logger = LoggerFactory.getLogger(SalaryService.class);

	/**
	 * @author Saurabh Rai
	 * @apiNote Calcualte salary breakup of employee
	 * @param employeeId
	 * @return
	 */
	public SalaryBreakupDto calculateSalaryBreakup(Long employeeId) {

		Employee employee = employeeRepository.findById(employeeId)
				.orElseThrow(() -> new EmployeeNotFoundException("Employee not found with ID: " + employeeId));

		double gross = employee.getSalary();
		double tds = 0.0;

		switch (employee.getCountry().toLowerCase()) {
		case "india":
			tds = gross * 0.10;
			break;
		case "united states":
			tds = gross * 0.12;
			break;
		default:
			tds = 0.0;
		}

		double net = gross - tds;

		EmployeeDto employeeDto = new EmployeeDto(employee);

		return new SalaryBreakupDto(employeeDto, gross, tds, net);
	}

	/**
	 * @author Saurabh Rai
	 * @apiNote Get salary metrics like min, max, average by country
	 * @param country
	 * @return
	 */
	public SalaryMetricsDto getSalaryMetricsByCountry(String country) {
		Double min = salaryRepository.findMinSalaryByCountry(country);
		Double max = salaryRepository.findMaxSalaryByCountry(country);
		Double avg = salaryRepository.findAverageSalaryByCountry(country);

		if (min == null || max == null || avg == null) {
			logger.warn("No employees found for country: {}", country);
			throw new CountryNotFoundException("No employees found for country: " + country);
		}

		return new SalaryMetricsDto(country, min, max, avg);
	}

	/**
	 * @author Saurabh Rai
	 * @apiNote Get average salary by job title
	 * @param jobTitle
	 * @return
	 */
	public SalaryMetricsDto getAverageSalaryByJobTitle(String jobTitle) {
		Double avgSalary = salaryRepository.findAverageSalaryByJobTitle(jobTitle);

		SalaryMetricsDto salaryMetricsDto = new SalaryMetricsDto(null, jobTitle, null, null, avgSalary);
		return salaryMetricsDto;
	}
}