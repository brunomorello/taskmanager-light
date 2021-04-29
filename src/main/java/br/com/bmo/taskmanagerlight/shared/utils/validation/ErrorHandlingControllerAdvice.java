package br.com.bmo.taskmanagerlight.shared.utils.validation;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import javax.validation.ConstraintViolationException;

@ControllerAdvice
public class ErrorHandlingControllerAdvice {

	@ExceptionHandler(ConstraintViolationException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	ValidationErrorResponse onConstraintViolationException(ConstraintViolationException e) {
		ValidationErrorResponse error = new ValidationErrorResponse();
		
		e.getConstraintViolations().forEach(violation -> {
			error.addViolation(new Violation(violation.getMessage(), violation.getPropertyPath().toString()));
		});
		
		return error;
	}
	
}
