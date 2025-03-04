package com.EmployeeService.service;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.EmployeeService.entity.Employee;

public interface EmployeeService {

    public Employee addEmployee(Employee employee);

    public List<Employee> getAllEmployee();

    public Employee getEmployeeById(long employeeId);

    public ResponseEntity<Map<String, String>> deleteEmployeeById(long employeeId);

    public Employee updateProfile(long employeeId, Employee employee);

    public Employee enrollIntoProject(long employeeId, long projectId);

    public Employee dropEmployeeFromProject(long employeeId, long projectId);
    
    public List<Employee> getAllEmployeesByProjectId(long projectId);

}
