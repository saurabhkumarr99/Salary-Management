package com.incubyte.SalaryManagement.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "Full name must not be blank")
	private String fullName;

	@NotBlank(message = "Job title must not be blank")
	private String jobTitle;

	@NotBlank(message = "Country must not be blank")
	private String country;

	@NotNull(message = "Salary must not be null")
	@Min(value = 1000, message = "Salary must be at least 1000")
	private double salary;

	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Employee(Long id, @NotBlank(message = "Full name must not be blank") String fullName,
			@NotBlank(message = "Job title must not be blank") String jobTitle,
			@NotBlank(message = "Country must not be blank") String country,
			@NotNull(message = "Salary must not be null") @Min(value = 1000, message = "Salary must be at least 1000") double salary) {
		super();
		this.id = id;
		this.fullName = fullName;
		this.jobTitle = jobTitle;
		this.country = country;
		this.salary = salary;
	}

	public Employee(@NotBlank(message = "Full name must not be blank") String fullName,
			@NotBlank(message = "Job title must not be blank") String jobTitle,
			@NotBlank(message = "Country must not be blank") String country,
			@NotNull(message = "Salary must not be null") @Min(value = 1000, message = "Salary must be at least 1000") double salary) {

		this.fullName = fullName;
		this.jobTitle = jobTitle;
		this.country = country;
		this.salary = salary;
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

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", fullName=" + fullName + ", jobTitle=" + jobTitle + ", country=" + country
				+ ", salary=" + salary + "]";
	}

}
