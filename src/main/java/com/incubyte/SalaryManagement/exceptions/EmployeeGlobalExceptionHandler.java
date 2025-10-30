package com.incubyte.SalaryManagement.exceptions;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.MethodArgumentNotValidException;

import com.incubyte.SalaryManagement.dto.ErrorResponseDto;

@RestControllerAdvice
public class EmployeeGlobalExceptionHandler {

	private static final Logger logger = LoggerFactory.getLogger(EmployeeGlobalExceptionHandler.class);

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResponseDto> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
		List<String> errors = ex.getBindingResult().getFieldErrors().stream()
				.map(error -> error.getField() + ": " + error.getDefaultMessage()).collect(Collectors.toList());

		ErrorResponseDto errorResponseDto = new ErrorResponseDto(LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(),
				"Validation failed", errors);

		logger.error("Validation failed: {}", errors);

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponseDto);
	}
}
