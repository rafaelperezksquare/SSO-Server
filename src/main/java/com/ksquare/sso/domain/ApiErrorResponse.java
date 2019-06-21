package com.ksquare.sso.domain;

import org.springframework.http.HttpStatus;

public class ApiErrorResponse {

	private HttpStatus status;
	
	private String error;
	
	
	private ApiErrorResponse() {
	}

	public ApiErrorResponse(HttpStatus status) {
		this();
		this.status = status;
	}

	public ApiErrorResponse(HttpStatus status, String error) {
		this();
		this.status = status;
		this.error = error;
	}
	
	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}
}
