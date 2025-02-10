package com.ProjectService.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.ProjectService.DTO.ProjectDTO;
import com.ProjectService.entity.Project;
import com.ProjectService.exception.NoProjectFoundByIdException;
import com.ProjectService.exception.NoProjectFoundException;

import java.util.List;


//Defines the operations to be performed on Project entities
public interface ProjectService {

    public Project addProject(Project project);

    public List<ProjectDTO> getAllProject();

    public Project getProjectById(long projectId);

    public String deleteProjectById(long projectId);
}
