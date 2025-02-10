package com.EmployeeService.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.EmployeeService.DTO.ProjectDTO;
import com.EmployeeService.entity.Employee;
import com.EmployeeService.exception.EmployeeNotFoundByIdException;
import com.EmployeeService.exception.EmployeeNotFoundException;
import com.EmployeeService.exception.ProjectNotFoundException;
import com.EmployeeService.externalServices.ProjectServices;
import com.EmployeeService.repo.EmployeeRepo;

@Service // Marks this class as a Spring service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	EmployeeRepo employeeRepo;

	@Autowired
	ProjectServices projectServices;

	// Add a new employee
	public Employee addEmployee(Employee employee) {
		return employeeRepo.save(employee);
	}

	// Get all employees
	public List<Employee> getAllEmployee() {
		List<Employee> employees = employeeRepo.findAll();
		if (employees.isEmpty()) {
			throw new EmployeeNotFoundException("Employee Not Found");
		}
		return employees;
	}

	// Get employee by ID
	public Employee getEmployeeById(long employeeId) {
		Employee employee = employeeRepo.findById(employeeId).orElse(null);
		if (employee == null) {
			throw new EmployeeNotFoundByIdException("Employee By Id " + employeeId + " is not Found");
		}
		return employee;
	}

	// Delete employee by ID
	public String deleteEmployeeById(long employeeId) {
		Employee employee = employeeRepo.findById(employeeId).orElse(null);
		if (employee == null) {
			throw new EmployeeNotFoundByIdException("Employee By Id " + employeeId + " is not Found to delete");
		}
		employeeRepo.deleteById(employeeId);
		return "Employee Deleted Successfully";
	}

	// Update employee profile
	public Employee updateProfile(long employeeId, Employee employee) {
		Employee employee1 = employeeRepo.findById(employeeId).orElse(null);

		if (employee1 == null) {
			throw new EmployeeNotFoundByIdException(
					"Employee Not  Found with Id " + employeeId + ", Update Unsuccessfull");
		}

		return employeeRepo.save(employee);
	}

	// Enroll employee into a project
	public Employee enrollIntoProject(long employeeId, long projectId) {
		Employee employee = employeeRepo.findById(employeeId).orElse(null);
		if (employee == null) {
			throw new EmployeeNotFoundByIdException(
					"Employee Not found with SEmployeeId " + employeeId + ", Enrollment Unsuccessfull");
		}

		ProjectDTO projectDTO = projectServices.getProjectById(projectId);

		employee.setProjectId(projectId);
		projectDTO.getEmployees().add(employee);

		return employeeRepo.save(employee);
	}

	// Drop employee from a project
	public Employee dropEmployeeFromProject(long employeeId, long projectId) {
		Employee employee = employeeRepo.findById(employeeId).orElse(null);
		if (employee == null) {
			throw new EmployeeNotFoundByIdException(
					"Employee Not found with Employee Id " + employeeId + ", Unregister from Project Unsuccessfull");
		}

		ProjectDTO projectDTO = projectServices.getProjectById(projectId);
		employee.setProjectId(0);
		projectDTO.getEmployees().remove(employee);
		return employeeRepo.save(employee);
	}

	// Get all employees by project ID
	public List<Employee> getAllEmployeesByProjectId(long projectId) {
		// TODO Auto-generated method stub
		return employeeRepo.findByProjectId(projectId);
	}

}