package com.ProjectService.exception;

public class NoProjectFoundException extends RuntimeException{

    public NoProjectFoundException(String msg){
        super(msg);
    }
}
