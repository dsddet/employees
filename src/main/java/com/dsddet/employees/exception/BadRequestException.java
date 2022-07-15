package com.dsddet.employees.exception;


public class BadRequestException extends RuntimeException {
    private String msg;

    public BadRequestException(){
        super();
    }

    public BadRequestException(String msg){
        super(msg);
        this.msg=msg;
    }
}
