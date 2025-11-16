package com.incubyte.SalaryManagement.dto;

import java.time.LocalDateTime;
import java.util.List;

public class ErrorResponseDto {

	private LocalDateTime timestamp;
	private String requestId;
	private int status;
	private String message;
	private List<String> errors;

	public ErrorResponseDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ErrorResponseDto(LocalDateTime timestamp, String requestId, int status, String message,
			List<String> errors) {
		super();
		this.timestamp = timestamp;
		this.requestId = requestId;
		this.status = status;
		this.message = message;
		this.errors = errors;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<String> getErrors() {
		return errors;
	}

	public void setErrors(List<String> errors) {
		this.errors = errors;
	}

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	@Override
	public String toString() {
		return "ErrorResponseDto [timestamp=" + timestamp + ", requestId=" + requestId + ", status=" + status
				+ ", message=" + message + ", errors=" + errors + "]";
	}

}
