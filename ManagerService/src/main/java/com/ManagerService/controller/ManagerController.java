package com.ManagerService.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ManagerService.DTO.ProjectDTO;
import com.ManagerService.entity.Manager;
import com.ManagerService.service.ManagerServiceImpl;

import jakarta.validation.Valid;

//@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/manager") // URL Path
public class ManagerController {

	@Autowired
	ManagerServiceImpl managerService;

	// Add a new manager
	@PostMapping("/add") // http://localhost:3434/manager/add
	public Manager addManager(@Valid @RequestBody Manager manager) {
		return managerService.addManager(manager);
	}

	// Get All Manager
	@GetMapping("/getAll") // http://localhost:3434/manager/getAll
	public List<Manager> getAllManager() {
		return managerService.getAllManager();
	}

	// Get manager by ID
	@GetMapping("/getById/{managerId}") // http://localhost:3434/manager/getById/2380220
	public Manager getManagerById(@PathVariable long managerId) {
		return managerService.getManagerById(managerId);
	}

	// Delete manager by ID
	  @DeleteMapping("/deleteById/{managerId}") // http://localhost:3434/manager/deleteById/2380220
	    public ResponseEntity<Map<String, String>> deleteManager(@PathVariable long managerId) {
	        return managerService.deleteManagerById(managerId);
	    }

	// Assign a different project to manager
	@PutMapping("/assignProject/{managerId}/{projectId}") // http://localhost:3434/manager/assignProject/2380220/1001
	public Manager assignProjectToManager(@PathVariable long managerId, @PathVariable long projectId) {
		return managerService.assignProjectToManager(managerId, projectId);
	}
	
	
	@PutMapping("/update/{managerId}") // http://localhost:3434/manager/update/2380220
    public Manager updateManager(@PathVariable long managerId, @RequestBody Manager manager) {
        return managerService.updateManager(managerId, manager);
    }

	// Get project managed by manager ID
	@GetMapping("/getProject/{managerId}") // http://localhost:3434/manager/getProject/2380220
	public ProjectDTO getProjectByManagerId(@PathVariable long managerId) {
		return managerService.getAssignProjectByManagerId(managerId);
	}
	
	@GetMapping("getManagerByProjectID/{projectId}")
	public Manager getManagerByProjectId(@PathVariable long projectId) {
		return managerService.getManagerByProjectId(projectId);
	}
}
