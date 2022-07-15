package com.dsddet.employees.service.impl;

import com.dsddet.employees.domain.Employee;
import com.dsddet.employees.exception.EmployeeNotFoundException;
import com.dsddet.employees.repository.EmployeeRepository;
import com.dsddet.employees.service.IEmployeeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class EmployeeService implements IEmployeeService {

    public final EmployeeRepository employeeRepository;

    @Override
    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id).orElseThrow(() -> new EmployeeNotFoundException("Employee with %d not found".formatted(id)));
    }

    @Override
    public Employee updateEmployee(Employee employee) {
        return null;
    }

    @Override
    public void deleteEmployee(Long id) throws EmployeeNotFoundException {
        try {
            employeeRepository.deleteById(id);
        } catch (Exception ex) {
            log.error("Employee with id {} does not exist", id);
            throw new EmployeeNotFoundException("Employee with id %d does not exist".formatted(id));
        }
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        try {
            return employeeRepository.save(employee);
        } catch (Exception ex) {
            log.error("Error ::: {}", ex);
        }
        return null;
    }
}
