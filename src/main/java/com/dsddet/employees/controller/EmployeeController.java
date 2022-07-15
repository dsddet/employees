package com.dsddet.employees.controller;

import com.dsddet.employees.domain.Employee;
import com.dsddet.employees.service.IEmployeeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/employee")
@Slf4j
@RequiredArgsConstructor
public class EmployeeController {

    private final IEmployeeService employeeService;

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployee(@PathVariable("id") Long id) {
        log.info("Retrieving employee with ID ::: %d".formatted(id));
        Employee emp = employeeService.getEmployeeById(id);
        return new ResponseEntity(emp, emp == null ? HttpStatus.NO_CONTENT : HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Employee> createEmployee(@Validated @RequestBody Employee employee) {
        log.info("Adding Employee with details  ::: %s".formatted(employee));
        return new ResponseEntity<>(employeeService.saveEmployee(employee), HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable("id") Long id, @Validated @RequestBody Employee employee) {
        employee.setId(id);
        return new ResponseEntity<>(employeeService.updateEmployee(employee), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteEmployee(@PathVariable("id") Long id) {
        log.info("Deleting employee with ID  ::: %s".formatted(id));
        employeeService.deleteEmployee(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/id")
    public ResponseEntity<Set<Employee>> getManyEmployeesByID(@RequestParam("list") Set<Long> ids){
        log.info("Getting employee IDs  ::: %s".formatted(ids.toString()));
        return new ResponseEntity<>(employeeService.getEmployeesById(ids),HttpStatus.OK);

    }


}
