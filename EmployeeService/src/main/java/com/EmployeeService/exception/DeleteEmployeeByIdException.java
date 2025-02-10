package com.EmployeeService.exception;

public class DeleteEmployeeByIdException extends RuntimeException{

    public DeleteEmployeeByIdException(String msg){
        super(msg);
    }
}
