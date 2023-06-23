package com.project.coffeshop.exception;

public class UnAuthorizeException extends RuntimeException{

    private String msg;

    public UnAuthorizeException(String msg){
        super(msg);
        this.msg = msg;
    }
}
