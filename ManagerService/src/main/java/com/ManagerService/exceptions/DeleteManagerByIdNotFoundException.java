package com.ManagerService.exceptions;

public class DeleteManagerByIdNotFoundException extends RuntimeException{
    public DeleteManagerByIdNotFoundException(String msg){
        super(msg);
    }
}
