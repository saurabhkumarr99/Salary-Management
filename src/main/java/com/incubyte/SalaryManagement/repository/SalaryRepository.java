package com.incubyte.SalaryManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.incubyte.SalaryManagement.model.Employee;

/**
 * Repository for fetching salary metrics (min, max, avg) based on country.
 */
public interface SalaryRepository extends JpaRepository<Employee, Long> {

	@Query("SELECT MIN(e.salary) FROM Employee e WHERE e.country = :country")
	Double findMinSalaryByCountry(@Param("country") String country);

	@Query("SELECT MAX(e.salary) FROM Employee e WHERE e.country = :country")
	Double findMaxSalaryByCountry(@Param("country") String country);

	@Query("SELECT AVG(e.salary) FROM Employee e WHERE e.country = :country")
	Double findAverageSalaryByCountry(@Param("country") String country);

	// Get average salary by job title
	@Query("SELECT AVG(e.salary) FROM Employee e WHERE e.jobTitle = :jobTitle")
	Double findAverageSalaryByJobTitle(@Param("jobTitle") String jobTitle);
}
