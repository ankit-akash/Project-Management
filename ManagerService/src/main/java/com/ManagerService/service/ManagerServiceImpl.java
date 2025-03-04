package com.ManagerService.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ManagerService.DTO.ProjectDTO;
import com.ManagerService.entity.Manager;
import com.ManagerService.exceptions.ManagerNotFoundByIdException;
import com.ManagerService.exceptions.ManagerNotFoundException;
import com.ManagerService.externalService.ProjectServices;
import com.ManagerService.repo.ManagerRepo;

@Service // Marks this class as a Spring service
public class ManagerServiceImpl implements ManagerService {

	@Autowired
	ManagerRepo managerRepo;

	@Autowired
	ProjectServices projectServices;

	// Add a new manager
	public Manager addManager(Manager manager) {
		return managerRepo.save(manager);
	}

	// Get all managers
	public List<Manager> getAllManager() {
		List<Manager> managers = managerRepo.findAll();
		if (managers.isEmpty()) {
			throw new ManagerNotFoundException("Managers record not found");
		}
		return managers;
	}

	// Assign project to manager
	public Manager getManagerById(long managerId) {
		Manager manager = managerRepo.findById(managerId).orElse(null);
		if (manager == null) {
			throw new ManagerNotFoundByIdException("Manager Not found by Id " + managerId);
		}
		return manager;
	}

	// Delete manager by ID
	public ResponseEntity<Map<String, String>> deleteManagerById(long managerId) {
		Manager manager = managerRepo.findById(managerId).orElse(null);
		managerRepo.deleteById(managerId);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Manager Deleted Successfully");
        return ResponseEntity.ok(response);

	}
	
	// Assign project to manager
		public Manager assignProjectToManager(long managerId, long projectId) {
			Manager manager = managerRepo.findById(managerId).orElse(null);
			if (manager == null) {
				throw new ManagerNotFoundByIdException("Manager not found by Id " + managerId);
			}
			ProjectDTO projectDTO = projectServices.getProjectByIdForManager(projectId);
			manager.setProjectId(projectDTO.getProjectId());

			return managerRepo.save(manager);
		}

	// Get project by manager ID
	public ProjectDTO getAssignProjectByManagerId(long managerId) {
		Manager manager = managerRepo.findById(managerId).orElse(null);
		if (manager == null) {
			throw new ManagerNotFoundByIdException("Manager not found by Id " + managerId);
		}
		long projectId = manager.getProjectId();
		ProjectDTO projectDTO = projectServices.getProjectByIdForManager(projectId);

		return projectDTO;
	}

	public Manager getManagerByProjectId(long projectId) {
		Manager manager = managerRepo.findById(projectId).orElse(null);
		if (manager==null) {
			throw new ManagerNotFoundException("Managers record not found");
		}
		return manager;
	}

    @Override
    public Manager updateManager(long managerId, Manager manager) {
        Manager existingManager = managerRepo.findById(managerId).orElse(null);
        if (existingManager == null) {
            throw new ManagerNotFoundByIdException("Manager not found by Id " + managerId);
        }
        existingManager.setManagerName(manager.getManagerName());
        existingManager.setManagerEmail(manager.getManagerEmail());
        existingManager.setProjectId(manager.getProjectId());
        return managerRepo.save(existingManager);
    }



}
