package com.dsddet.employees.controller;

import com.dsddet.employees.exception.EmployeeNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@Slf4j
public class EmployeeControllerAdvice {

    // 409
    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<Object> throwemployeeNotFoundException(){
        log.error("User not found");

        return new ResponseEntity<>("User not found",HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> thowGeneralException(){
        log.error("User not found");

        return new ResponseEntity<>("Oops!! - Something bad happened",HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
