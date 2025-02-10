package com.ProjectService.exception;

public class DeleteProjectByIdException extends RuntimeException{
    public DeleteProjectByIdException(String msg){
        super(msg);
    }
}
