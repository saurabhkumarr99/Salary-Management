package com.incubyte.SalaryManagement.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class SalaryMetricsDto {

	private String country;
	private String jobTitle;
	private Double minSalary;
	private Double maxSalary;
	private Double averageSalary;

	public SalaryMetricsDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SalaryMetricsDto(String country, Double minSalary, Double maxSalary, Double averageSalary) {
		super();
		this.country = country;
		this.minSalary = minSalary;
		this.maxSalary = maxSalary;
		this.averageSalary = averageSalary;
	}

	public SalaryMetricsDto(String country, String jobTitle, Double minSalary, Double maxSalary, Double averageSalary) {
		super();
		this.country = country;
		this.jobTitle = jobTitle;
		this.minSalary = minSalary;
		this.maxSalary = maxSalary;
		this.averageSalary = averageSalary;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Double getMinSalary() {
		return minSalary;
	}

	public void setMinSalary(Double minSalary) {
		this.minSalary = minSalary;
	}

	public Double getMaxSalary() {
		return maxSalary;
	}

	public void setMaxSalary(Double maxSalary) {
		this.maxSalary = maxSalary;
	}

	public Double getAverageSalary() {
		return averageSalary;
	}

	public void setAverageSalary(Double averageSalary) {
		this.averageSalary = averageSalary;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	@Override
	public String toString() {
		return "SalaryMetricsDto [country=" + country + ", jobTitle=" + jobTitle + ", minSalary=" + minSalary
				+ ", maxSalary=" + maxSalary + ", averageSalary=" + averageSalary + "]";
	}

}
