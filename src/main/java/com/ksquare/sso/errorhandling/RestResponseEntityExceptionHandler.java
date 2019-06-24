package com.ksquare.sso.errorhandling;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.ksquare.sso.domain.ApiErrorResponse;

@ControllerAdvice
public class RestResponseEntityExceptionHandler 
  extends ResponseEntityExceptionHandler {
 
    @ExceptionHandler(value = { RuntimeException.class})
    protected ResponseEntity<Object> handleConflict(RuntimeException exception, WebRequest request) {
        String responseBody = "Something went wrong";
        HttpStatus status = HttpStatus.NOT_FOUND;
        
        if(exception.getClass() == InvalidDataAccessApiUsageException.class) {
    		responseBody = "Invalid parameter in the path.";
    	}
    	if(exception.getClass() == NullPointerException.class) {
    		responseBody = "Invalid parameter in the path or in the body.";
    	}
    	if(exception.getClass() == DataIntegrityViolationException.class) {
    		responseBody = "Trying to duplicate a resource.";
    		status = HttpStatus.CONFLICT;
    	}
    	
    	ApiErrorResponse apiErrorResponse = new ApiErrorResponse(status, responseBody);
        
        return new ResponseEntity<>(apiErrorResponse, status);
    }
}
