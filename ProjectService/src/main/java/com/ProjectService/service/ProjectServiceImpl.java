package com.ProjectService.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ProjectService.DTO.EmployeeDTO;
import com.ProjectService.DTO.ProjectDTO;
import com.ProjectService.entity.Project;
import com.ProjectService.exception.NoProjectFoundByIdException;
import com.ProjectService.exception.NoProjectFoundException;
import com.ProjectService.exception.SaveProjectException;
import com.ProjectService.externalServices.EmployeeServices;
import com.ProjectService.repo.ProjectRepo;

@Service // Marks this class as a Spring service
public class ProjectServiceImpl implements ProjectService {

	@Autowired
	ProjectRepo projectRepo;

	@Autowired
	EmployeeServices employeeServices;

	// Add a new project
	public Project addProject(Project project) {
		return projectRepo.save(project);
	}

	// Get all projects
	public List<ProjectDTO> getAllProject() throws NoProjectFoundException {
		List<Project> projects = projectRepo.findAll();
		if (projects.isEmpty()) {
			throw new NoProjectFoundException("There is no project present");
		}
		List<ProjectDTO> projectDTOS = new ArrayList<>();

		for (Project project : projects) {
			ProjectDTO projectDTO = new ProjectDTO(project.getProjectId(), project.getProjectName(),
					project.getProjectDesc());
			projectDTOS.add(projectDTO);
		}

		return projectDTOS;
	}

	// Get project by ID
	public Project getProjectById(long projectId) {
		Project project = projectRepo.findById(projectId).orElse(null);
		if (project == null) {
			throw new NoProjectFoundByIdException("No Project with id " + projectId + " is present");
		}
		List<EmployeeDTO> listOfEmployee = employeeServices.getAllEmployeesByProjectId(projectId);
		project.setEmployees(listOfEmployee);
		return project;
	}

	// Delete project by ID
	public String deleteProjectById(long projectId) {
		Project project = projectRepo.findById(projectId).orElse(null);
		if (project == null) {
			throw new NoProjectFoundByIdException("No Project with Id " + projectId + " is present to delete");
		}
		projectRepo.deleteById(projectId);
		return "Deleted";
	}

	// Get project by ID for manager
	public Project getProjectByIdForManager(long projectId) {
		Project project = projectRepo.findById(projectId).orElse(null);
		if (project == null) {
			throw new NoProjectFoundByIdException("No Project with id " + projectId + " is present");
		}
		return project;
	}
}
