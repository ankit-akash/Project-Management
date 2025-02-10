package com.ManagerService.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice // Global exception handler for Manager Service
public class GlobalExceptionHandlerForManager {

	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(ManagerNotFoundException.class)
	public Map<String, String> handleManagerNotFound(ManagerNotFoundException exception) {
		Map<String, String> errorMap = new HashMap<>();
		errorMap.put("ErrorMessage", exception.getMessage());
		return errorMap;
	}

	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(ManagerNotFoundByIdException.class)
	public Map<String, String> handleManagerById(ManagerNotFoundByIdException exception) {
		Map<String, String> errorMap = new HashMap<>();
		errorMap.put("ErrorMessage", exception.getMessage());
		return errorMap;
	}

	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(DeleteManagerByIdNotFoundException.class)
	public Map<String, String> handleDeleteManagerById(DeleteManagerByIdNotFoundException exception) {
		Map<String, String> errorMap = new HashMap<>();
		errorMap.put("ErrorMessage", exception.getMessage());
		return errorMap;
	}

	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(ProjectIdNotFoundException.class)
	public Map<String, String> handleProjectNotFound(ProjectIdNotFoundException exception) {
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
