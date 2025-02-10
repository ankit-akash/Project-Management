package com.EmployeeService.exception;

public class EmployeeNotFoundByIdException extends RuntimeException{

    public EmployeeNotFoundByIdException(String msg){
        super(msg);
    }
}
