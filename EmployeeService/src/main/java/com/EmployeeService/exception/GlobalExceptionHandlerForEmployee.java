package com.EmployeeService.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice // Global exception handler for Employee Service
public class GlobalExceptionHandlerForEmployee {

	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(EmployeeNotFoundException.class)
	public Map<String, String> handleEmployeeNotFound(EmployeeNotFoundException exception) {
		Map<String, String> errorMap = new HashMap<>();
		errorMap.put("Error Message", exception.getMessage());
		return errorMap;
	}

	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(EmployeeNotFoundByIdException.class)
	public Map<String, String> handleEmployeeNotFoundById(EmployeeNotFoundByIdException exception) {
		Map<String, String> errorMap = new HashMap<>();
		errorMap.put("Error Message", exception.getMessage());
		return errorMap;
	}

	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(DeleteEmployeeByIdException.class)
	public Map<String, String> handleDeleteEmployeeById(DeleteEmployeeByIdException exception) {
		Map<String, String> errorMap = new HashMap<>();
		errorMap.put("ErrorMessage", exception.getMessage());
		return errorMap;
	}

	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(ProjectNotFoundException.class)
	public Map<String, String> handleProjectNotFound(ProjectNotFoundException exception) {
		Map<String, String> errorMap = new HashMap<>();
		errorMap.put("ErrorMessage", exception.getMessage());
		return errorMap;
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
		Map<String, String> errorMap = new HashMap<>();
		ex.getBindingResult().getFieldErrors()
				.forEach(error -> errorMap.put(error.getField(), error.getDefaultMessage()));
		return errorMap;
	}

}
