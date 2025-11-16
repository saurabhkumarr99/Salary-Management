package com.incubyte.SalaryManagement.exceptions;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;

import com.incubyte.SalaryManagement.dto.ErrorResponseDto;

@RestControllerAdvice
public class GlobalExceptionHandler {

	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	// Handle global validation exceptions
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResponseDto> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
		List<String> errors = ex.getBindingResult().getFieldErrors().stream()
				.map(error -> error.getField() + ": " + error.getDefaultMessage()).collect(Collectors.toList());

		String requestId = MDC.get("requestId");

		ErrorResponseDto errorResponseDto = new ErrorResponseDto(LocalDateTime.now(), requestId,
				HttpStatus.BAD_REQUEST.value(), "Validation failed", errors);

		logger.error("Validation failed: {}", errors);

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponseDto);
	}

	// Handle global employee not found exceptions
	@ExceptionHandler(EmployeeNotFoundException.class)
	public ResponseEntity<ErrorResponseDto> handleEmployeeNotFound(EmployeeNotFoundException ex) {

		String requestId = MDC.get("requestId");

		ErrorResponseDto errorResponseDto = new ErrorResponseDto(LocalDateTime.now(), requestId,
				HttpStatus.NOT_FOUND.value(), "Employee Not Found", List.of(ex.getMessage()));

		logger.error("Employee not found: {}", ex.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponseDto);
	}

	// Handle global country not found exception
	@ExceptionHandler(CountryNotFoundException.class)
	public ResponseEntity<ErrorResponseDto> handleCountryNotFound(CountryNotFoundException ex) {

		String requestId = MDC.get("requestId");

		ErrorResponseDto errorResponse = new ErrorResponseDto(LocalDateTime.now(), requestId,
				HttpStatus.NOT_FOUND.value(), "Country not found", List.of(ex.getMessage()));
		logger.error("Country not found: {}", ex.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
	}

	// Handle global job title not found exception
	@ExceptionHandler(JobTitleNotFoundException.class)
	public ResponseEntity<ErrorResponseDto> handleJobTitleNotFound(JobTitleNotFoundException ex) {

		String requestId = MDC.get("requestId");

		ErrorResponseDto errorResponse = new ErrorResponseDto(LocalDateTime.now(), requestId,
				HttpStatus.NOT_FOUND.value(), "Job Title not found", List.of(ex.getMessage()));
		logger.error("Country not found: {}", ex.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
	}

	// Handle all other unexpected exceptions (fallback)
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponseDto> handleGlobalExceptions(Exception ex) {
		logger.error("Unexpected exception occurred: {}", ex.getMessage(), ex);

		String requestId = MDC.get("requestId");

		ErrorResponseDto errorResponseDto = new ErrorResponseDto(LocalDateTime.now(), requestId,
				HttpStatus.INTERNAL_SERVER_ERROR.value(), "Internal Server Error", List.of(ex.getMessage()));

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponseDto);
	}

}
