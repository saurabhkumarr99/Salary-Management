package com.incubyte.SalaryManagement.exceptions;

public class JobTitleNotFoundException extends RuntimeException {
    public JobTitleNotFoundException(String message) {
        super(message);
    }
}