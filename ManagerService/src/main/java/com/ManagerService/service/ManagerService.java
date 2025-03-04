package com.ManagerService.service;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.ManagerService.DTO.ProjectDTO;
import com.ManagerService.entity.Manager;

public interface ManagerService {

    public List<Manager> getAllManager();

    public Manager getManagerById(long managerId);

    public ResponseEntity<Map<String, String>>  deleteManagerById(long managerId);

    public ProjectDTO getAssignProjectByManagerId(long managerId);
    
    public Manager assignProjectToManager(long managerId,long projectId);
    
    public Manager getManagerByProjectId(long projectId);
    
    public Manager updateManager(long managerId, Manager manager);

}
