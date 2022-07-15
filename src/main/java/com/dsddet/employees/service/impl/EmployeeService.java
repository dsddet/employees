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

    /**
     * Retrieve an employee record by ID
     * @param id
     * @return
     */
    @Override
    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id).orElse(null);
    }

    /**
     * Update an employee record
     * @param empUpdate
     * @return
     * @throws RuntimeException
     */
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

    /**
     * Delete existing employee record or throw an exception when provided ID cannot be found
     * @param id
     */
    @Override
    public void deleteEmployee(Long id) {

        try {
            employeeRepository.deleteById(id);
        } catch (Exception ex) {
            throw new EmployeeNotFoundException("Employee with id %d does not exist".formatted(id));
        }
    }

    /**
     * Add an employee record
     * @param employee
     * @return
     * @throws RuntimeException
     */
    @Override
    public Employee saveEmployee(Employee employee) throws RuntimeException {
        if(employee==null){
            throw new BadRequestException("Missing employee details");
        }
        try {
            return employeeRepository.save(employee);
        } catch (Exception ex) {
            throw new RuntimeException("Error while saving employee  ::: %s".formatted(ex.getMessage()));
        }

    }
}
