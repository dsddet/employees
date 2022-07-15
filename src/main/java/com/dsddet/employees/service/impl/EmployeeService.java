package com.dsddet.employees.service.impl;

import com.dsddet.employees.domain.Employee;
import com.dsddet.employees.exception.BadRequestException;
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
    public Employee updateEmployee(Employee empUpdate) throws RuntimeException {
        Employee employee=getEmployeeById(empUpdate.getId());

        employee.setDepartment(empUpdate.getDepartment());
        employee.setEmail(empUpdate.getEmail());
        employee.setFirstName(empUpdate.getFirstName());
        employee.setLastName(empUpdate.getLastName());
        employee.setStartDate(empUpdate.getStartDate());
        employee.setJobTitle(empUpdate.getJobTitle());


        return employeeRepository.save(employee);
    }

    @Override
    public void deleteEmployee(Long id) {
        try {
            employeeRepository.deleteById(id);
        } catch (Exception ex) {
            throw new EmployeeNotFoundException("Employee with id %d does not exist".formatted(id));
        }
    }

    @Override
    public Employee saveEmployee(Employee employee) throws RuntimeException {
        if(employee==null){
            throw new BadRequestException("Employee details cannot be empty");
        }
        try {
            return employeeRepository.save(employee);
        } catch (Exception ex) {
            throw new RuntimeException("Error while saving employee  ::: %s".formatted(ex.getMessage()));
        }

    }
}
