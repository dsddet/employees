package com.dsddet.employees.controller;

import com.dsddet.employees.exception.BadRequestException;
import com.dsddet.employees.exception.EmployeeNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
@Slf4j
public class EmployeeControllerAdvice {

    // 409
    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<Object> employeeNotFound(RuntimeException ex, WebRequest request){
        log.error("Employee not found ::: {}",ex.getMessage());
        return new ResponseEntity<>("Oops!! ::: %s".formatted(ex.getMessage()),HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> unknownIssue(RuntimeException ex, WebRequest request){
        log.error("Oops!! - Something bad happened ::: {}",ex.getMessage());
        return new ResponseEntity<>("Oops!! ::: %s".formatted(ex.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<Object> badRequest(RuntimeException ex, WebRequest request){
        log.error("Bad request due to ::: ",ex.getMessage());
        return new ResponseEntity<>("Bad Request due to ::: %s".formatted(ex.getMessage()),HttpStatus.BAD_REQUEST);
    }


}
