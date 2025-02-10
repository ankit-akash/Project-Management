package com.EmployeeService.externalServices;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.EmployeeService.DTO.ProjectDTO;

@FeignClient(name = "PROJECTSERVICE", url = "http://localhost:8055/project") 
// Feign client for Project Service
public interface ProjectServices {

	// Get project by ID
	@GetMapping("/getById/{projectId}")
	public ProjectDTO getProjectById(@PathVariable("projectId") long projectId);

}
