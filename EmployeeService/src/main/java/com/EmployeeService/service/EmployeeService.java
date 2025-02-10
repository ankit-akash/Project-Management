package com.EmployeeService.service;

import org.springframework.web.bind.annotation.PathVariable;

import com.EmployeeService.entity.Employee;

import java.util.List;

public interface EmployeeService {

    public Employee addEmployee(Employee employee);

    public List<Employee> getAllEmployee();

    public Employee getEmployeeById(long employeeId);

    public String deleteEmployeeById(long employeeId);

    public Employee updateProfile(long employeeId, Employee employee);

    public Employee enrollIntoProject(long employeeId, long projectId);

    public Employee dropEmployeeFromProject(long employeeId, long projectId);
    
    public List<Employee> getAllEmployeesByProjectId(long projectId);

}
