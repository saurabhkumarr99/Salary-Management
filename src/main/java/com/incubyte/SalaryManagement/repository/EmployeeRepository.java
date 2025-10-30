package com.incubyte.SalaryManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.incubyte.SalaryManagement.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
