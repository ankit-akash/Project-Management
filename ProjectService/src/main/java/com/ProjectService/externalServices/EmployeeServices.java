package com.ProjectService.externalServices;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.ProjectService.DTO.EmployeeDTO;

@FeignClient(name = "EMPLOYEESERVICE",url="http://localhost:8050/employee")		// Feign client for Employee Service
public interface EmployeeServices {

	
    // Get all employees by project ID
    @GetMapping("/getAllEmployeesByProjectId/{projectId}")
    public List<EmployeeDTO> getAllEmployeesByProjectId(@PathVariable long projectId);

}
