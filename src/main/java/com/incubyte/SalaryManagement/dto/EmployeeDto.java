package com.incubyte.SalaryManagement.dto;

import com.incubyte.SalaryManagement.model.Employee;

public class EmployeeDto {

	private Long id;
	private String fullName;
	private String country;

	public EmployeeDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EmployeeDto(Long id, String fullName, String country) {
		super();
		this.id = id;
		this.fullName = fullName;
		this.country = country;
	}

	public EmployeeDto(Employee employee) {
		this.id = employee.getId();
		this.fullName = employee.getFullName();
		this.country = employee.getCountry();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@Override
	public String toString() {
		return "EmployeeDto [id=" + id + ", fullName=" + fullName + ", country=" + country + "]";
	}

}
