package com.dsddet.employees.exception;

public class EmployeeNotFoundException extends RuntimeException {
    private String msg;

    public EmployeeNotFoundException(){

    }

    public EmployeeNotFoundException(String msg){
        this.msg=msg;
    }
}
