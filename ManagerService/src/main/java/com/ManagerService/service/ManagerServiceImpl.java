package com.ManagerService.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ManagerService.DTO.ProjectDTO;
import com.ManagerService.entity.Manager;
import com.ManagerService.exceptions.DeleteManagerByIdNotFoundException;
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
	public String deleteManagerById(long managerId) {
		Manager manager = managerRepo.findById(managerId).orElse(null);
		if (manager == null) {
			throw new DeleteManagerByIdNotFoundException("Manager Delete Unsuccessfull");
		}
		managerRepo.deleteById(managerId);
		return "Manager Deleted Successfully";

	}

	// Get project managed by manager ID
	public ProjectDTO getAssignProjectByManagerId(long managerId) {
		Manager manager = managerRepo.findById(managerId).orElse(null);
		if (manager == null) {
			throw new ManagerNotFoundByIdException("Manager not found by Id " + managerId);
		}
		long projectId = manager.getProjectId();
		ProjectDTO projectDTO = projectServices.getProjectByIdForManager(projectId);

		return projectDTO;
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

}
