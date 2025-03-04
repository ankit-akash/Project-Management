package com.ProjectService.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ProjectService.DTO.ProjectDTO;
import com.ProjectService.entity.Project;
import com.ProjectService.service.ProjectServiceImpl;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

//@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/project")
@AllArgsConstructor
public class ProjectController {

	ProjectServiceImpl projectService;

	// Add a new project
	@PostMapping("/add") // http://localhost:3434/project/add
	public Project addProject(@Valid @RequestBody Project project) {
		return projectService.addProject(project);
	}

	// Get all project
	@GetMapping("/getAll") // http://localhost:3434/project/getAll
	public List<ProjectDTO> getAllProject() {
		return projectService.getAllProject();
	}

	// Get project by ID
	@GetMapping("/getById/{projectId}") // http://localhost:3434/project/getById/1001
	public Project getProjectById(@PathVariable long projectId) {
		return projectService.getProjectById(projectId);
	}

	// Get project by ID for manager
	@GetMapping("/getByIdForManager/{projectId}") // http://localhost:3434/project/getByIdForManager/1001
	public Project getProjectByIdForManager(@PathVariable long projectId) {
		return projectService.getProjectByIdForManager(projectId);
	}

	// Update project details
	@PutMapping("/update/{projectId}") // http://localhost:3434/project/update/1001
	public Project updateProject(@PathVariable long projectId, @Valid @RequestBody Project projectDetails) {
	    return projectService.updateProject(projectId, projectDetails);
	}
	
	// Delete project by ID
	@DeleteMapping("/deleteById/{projectId}") // http://localhost:3434/project/deletebyId/1001
	public Project deleteProjectById(@PathVariable long projectId) {
		return projectService.deleteProjectById(projectId);
	}
}
