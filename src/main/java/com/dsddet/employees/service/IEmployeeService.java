package com.dsddet.employees.service;

import com.dsddet.employees.domain.Employee;

public interface IEmployeeService {
    Employee getEmployeeById(Long id);
    Employee updateEmployee(Employee employee);
    void deleteEmployee(Long id);
    Employee saveEmployee(Employee employee);
}
