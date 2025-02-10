package com.ProjectService.exception;

public class NoProjectFoundByIdException extends RuntimeException{
    public NoProjectFoundByIdException(String msg){
        super(msg);
    }
}
