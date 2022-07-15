package com.dsddet.employees.service;

import com.dsddet.employees.domain.Employee;

import java.util.Set;

public interface IEmployeeService {
    Employee getEmployeeById(Long id);
    Employee updateEmployee(Employee employee);
    void deleteEmployee(Long id);
    Employee saveEmployee(Employee employee);
    Set<Employee> getEmployeesById(Set<Long> id);
}
