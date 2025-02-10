package com.ManagerService.exceptions;

public class ProjectIdNotFoundException extends RuntimeException{
    public ProjectIdNotFoundException(String msg){
        super(msg);
    }
}
