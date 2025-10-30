package com.incubyte.SalaryManagement.dto;

public class SalaryMetricsDto {

	private String country;
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

	@Override
	public String toString() {
		return "SalaryMetricsDto [country=" + country + ", minSalary=" + minSalary + ", maxSalary=" + maxSalary
				+ ", averageSalary=" + averageSalary + "]";
	}

}
