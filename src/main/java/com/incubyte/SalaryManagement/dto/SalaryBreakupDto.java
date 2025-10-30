package com.incubyte.SalaryManagement.dto;

public class SalaryBreakupDto {

	private double grossSalary;
	private double tds;
	private double netSalary;

	public SalaryBreakupDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SalaryBreakupDto(double grossSalary, double tds, double netSalary) {
		super();
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

	@Override
	public String toString() {
		return "SalaryBreakupDto [grossSalary=" + grossSalary + ", tds=" + tds + ", netSalary=" + netSalary + "]";
	}

}
