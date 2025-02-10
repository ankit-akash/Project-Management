package com.EmployeeService.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.EmployeeService.entity.Employee;
import com.EmployeeService.service.EmployeeServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/employee")
// for Employee Service
public class EmployeeController {

    @Autowired
    EmployeeServiceImpl employeeService;


    // Add employees
    @PostMapping("/add")			// http://localhost:3434/employee/add
    public Employee addEmployee(@Valid @RequestBody Employee employee){
        return employeeService.addEmployee(employee);
    }

    // Get all employees
    @GetMapping("/getAll")				// http://localhost:3434/employee/getAll
    public List<Employee> getAllEmployee(){
        return employeeService.getAllEmployee();
    }

    // Get employee by EMPID
    @GetMapping("/getById/{employeeId}")		// http://localhost:3434/employee/getById/112233
    public Employee getEmployeeById(long employeeId){
        return employeeService.getEmployeeById(employeeId);
    }

    //Delete Emp By EMPID
    @DeleteMapping("/deleteById/{employeeId}")	// http://localhost:3434/employee/deleteById/112233
    public String deleteEmployeeById(@PathVariable long employeeId){
        return employeeService.deleteEmployeeById(employeeId);
    }

    //Update the Emp by EMPID
    @PutMapping("/update/{employeeId}")			// http://localhost:3434/employee/update/112233
    public Employee updateEmployeeProfile(@PathVariable long employeeId,@RequestBody Employee employee){
        return employeeService.updateProfile(employeeId,employee);
    }

    //Enroll employee into a project
    @PutMapping("enroll/{employeeId}/{projectId}")		// http://localhost:3434/employee/enroll/112233/1001
    public Employee enrollIntoProject(@PathVariable long employeeId,@PathVariable long projectId){
        return employeeService.enrollIntoProject(employeeId,projectId);
    }

    //Drop employee from a project 
    @DeleteMapping("/drop/{employeeId}/{projectId}")		// http://localhost:3434/employee/drop/112233/1001
    public Employee dropEmployeeFromProject(@PathVariable long employeeId,@PathVariable long projectId){
        return employeeService.dropEmployeeFromProject(employeeId,projectId);
    }

    // Get all employees by project ID
    @GetMapping("/getAllEmployeesByProjectId/{projectId}")	// http://localhost:3434/employee/getAllEmployeesByProjectId/1001
    public List<Employee> getAllEmployeesByProjectId(@PathVariable long projectId) {
        return employeeService.getAllEmployeesByProjectId(projectId);
    }
}
