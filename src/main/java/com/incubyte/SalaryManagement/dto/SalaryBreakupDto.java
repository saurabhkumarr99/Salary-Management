package com.incubyte.SalaryManagement.dto;

public class SalaryBreakupDto {

	private EmployeeDto employeeDto;
	private double grossSalary;
	private double tds;
	private double netSalary;

	public SalaryBreakupDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SalaryBreakupDto(EmployeeDto employeeDto, double grossSalary, double tds, double netSalary) {
		super();
		this.employeeDto = employeeDto;
		this.grossSalary = grossSalary;
		this.tds = tds;
		this.netSalary = netSalary;
	}

	public double getGrossSalary() {
		return grossSalary;
	}

	public void setGrossSalary(double grossSalary) {
		this.grossSalary = grossSalary;
	}

	public double getTds() {
		return tds;
	}

	public void setTds(double tds) {
		this.tds = tds;
	}

	public double getNetSalary() {
		return netSalary;
	}

	public void setNetSalary(double netSalary) {
		this.netSalary = netSalary;
	}

	public EmployeeDto getEmployeeDto() {
		return employeeDto;
	}

	public void setEmployeeDto(EmployeeDto employeeDto) {
		this.employeeDto = employeeDto;
	}

	@Override
	public String toString() {
		return "SalaryBreakupDto [employeeDto=" + employeeDto + ", grossSalary=" + grossSalary + ", tds=" + tds
				+ ", netSalary=" + netSalary + "]";
	}

}
