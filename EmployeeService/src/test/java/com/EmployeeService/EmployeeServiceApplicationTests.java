package com.EmployeeService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.EmployeeService.DTO.ProjectDTO;
import com.EmployeeService.entity.Employee;
import com.EmployeeService.exception.EmployeeNotFoundByIdException;
import com.EmployeeService.exception.EmployeeNotFoundException;
import com.EmployeeService.repo.EmployeeRepo;
import com.EmployeeService.service.EmployeeServiceImpl;
import com.EmployeeService.externalServices.ProjectServices;

public class EmployeeServiceApplicationTests {

    @Mock
    private EmployeeRepo employeeRepo;

    @Mock
    private ProjectServices projectServices;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddEmployee() {
        Employee employee = new Employee();
        employee.setEmployeeId(1L);
        employee.setEmployeeName("Test Employee");

        when(employeeRepo.save(employee)).thenReturn(employee);

        Employee result = employeeService.addEmployee(employee);
        assertEquals(employee, result);
    }

    @Test
    public void testGetAllEmployee() {
        Employee employee = new Employee();
        employee.setEmployeeId(1L);
        employee.setEmployeeName("Test Employee");

        List<Employee> employees = Arrays.asList(employee);

        when(employeeRepo.findAll()).thenReturn(employees);

        List<Employee> result = employeeService.getAllEmployee();
        assertEquals(employees, result);
    }

    @Test
    public void testGetAllEmployee_EmptyList() {
        when(employeeRepo.findAll()).thenReturn(Arrays.asList());

        assertThrows(EmployeeNotFoundException.class, () -> {
            employeeService.getAllEmployee();
        });
    }

    @Test
    public void testGetEmployeeById() {
        Employee employee = new Employee();
        employee.setEmployeeId(1L);
        employee.setEmployeeName("Test Employee");

        when(employeeRepo.findById(1L)).thenReturn(Optional.of(employee));

        Employee result = employeeService.getEmployeeById(1L);
        assertEquals(employee, result);
    }

    @Test
    public void testGetEmployeeById_NotFound() {
        when(employeeRepo.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EmployeeNotFoundByIdException.class, () -> {
            employeeService.getEmployeeById(1L);
        });
    }

    @Test
    public void testDeleteEmployeeById() {
        Employee employee = new Employee();
        employee.setEmployeeId(1L);
        employee.setEmployeeName("Test Employee");

        when(employeeRepo.findById(1L)).thenReturn(Optional.of(employee));
        doNothing().when(employeeRepo).deleteById(1L);

        String result = employeeService.deleteEmployeeById(1L);
        assertEquals("Employee Deleted Successfully", result);
    }

    @Test
    public void testDeleteEmployeeById_NotFound() {
        when(employeeRepo.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EmployeeNotFoundByIdException.class, () -> {
            employeeService.deleteEmployeeById(1L);
        });
    }

    @Test
    public void testUpdateProfile() {
        Employee employee = new Employee();
        employee.setEmployeeId(1L);
        employee.setEmployeeName("Test Employee");

        when(employeeRepo.findById(1L)).thenReturn(Optional.of(employee));
        when(employeeRepo.save(employee)).thenReturn(employee);

        Employee result = employeeService.updateProfile(1L, employee);
        assertEquals(employee, result);
    }

    @Test
    public void testUpdateProfile_NotFound() {
        Employee employee = new Employee();
        employee.setEmployeeId(1L);
        employee.setEmployeeName("Test Employee");

        when(employeeRepo.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EmployeeNotFoundByIdException.class, () -> {
            employeeService.updateProfile(1L, employee);
        });
    }

    @Test
    public void testEnrollIntoProject() {
        Employee employee = new Employee();
        employee.setEmployeeId(1L);
        employee.setEmployeeName("Test Employee");

        ProjectDTO projectDTO = new ProjectDTO();
        projectDTO.setProjectId(1L);

        when(employeeRepo.findById(1L)).thenReturn(Optional.of(employee));
        when(projectServices.getProjectById(1L)).thenReturn(projectDTO);
        when(employeeRepo.save(employee)).thenReturn(employee);

        Employee result = employeeService.enrollIntoProject(1L, 1L);
        assertEquals(employee, result);
    }

    @Test
    public void testEnrollIntoProject_EmployeeNotFound() {
        when(employeeRepo.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EmployeeNotFoundByIdException.class, () -> {
            employeeService.enrollIntoProject(1L, 1L);
        });
    }

    @Test
    public void testDropEmployeeFromProject() {
        Employee employee = new Employee();
        employee.setEmployeeId(1L);
        employee.setEmployeeName("Test Employee");

        ProjectDTO projectDTO = new ProjectDTO();
        projectDTO.setProjectId(1L);

        when(employeeRepo.findById(1L)).thenReturn(Optional.of(employee));
        when(projectServices.getProjectById(1L)).thenReturn(projectDTO);
        when(employeeRepo.save(employee)).thenReturn(employee);

        Employee result = employeeService.dropEmployeeFromProject(1L, 1L);
        assertEquals(employee, result);
    }

    @Test
    public void testDropEmployeeFromProject_EmployeeNotFound() {
        when(employeeRepo.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EmployeeNotFoundByIdException.class, () -> {
            employeeService.dropEmployeeFromProject(1L, 1L);
        });
    }

    @Test
    public void testGetAllEmployeesByProjectId() {
        Employee employee = new Employee();
        employee.setEmployeeId(1L);
        employee.setEmployeeName("Test Employee");

        List<Employee> employees = Arrays.asList(employee);

        when(employeeRepo.findByProjectId(1L)).thenReturn(employees);

        List<Employee> result = employeeService.getAllEmployeesByProjectId(1L);
        assertEquals(employees, result);
    }
}