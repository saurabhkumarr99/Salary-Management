package com.incubyte.SalaryManagement.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String fullName;
	private String jobTitle;
	private String country;
	private double salary;

	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Employee(Long id, String fullName, String jobTitle, String country, double salary) {
		super();
		this.id = id;
		this.fullName = fullName;
		this.jobTitle = jobTitle;
		this.country = country;
		this.salary = salary;
	}

	public Employee(String fullName, String jobTitle, String country, double salary) {
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
