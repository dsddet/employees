package com.dsddet.employees.exception;

public class EmployeeNotFoundException extends RuntimeException {
    private String msg;

    public EmployeeNotFoundException(){
        super();

    }

    public EmployeeNotFoundException(String msg){
        super(msg);
        this.msg=msg;
    }
}
