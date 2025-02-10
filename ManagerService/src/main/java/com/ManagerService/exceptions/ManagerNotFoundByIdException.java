package com.ManagerService.exceptions;

public class ManagerNotFoundByIdException extends RuntimeException{
    public ManagerNotFoundByIdException(String msg){
        super(msg);
    }
}
